package com.climbingtrackerapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.climbingtrackerapp.domain.model.Climb
import com.climbingtrackerapp.util.climbingGrade.Yosemite
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration
import java.util.Collections.copy
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class RecordService : LifecycleService() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private var climbSessionDurationIncrementerJob: Job? = null
    private var climbCurrentDurationIncrementerJob: Job? = null

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("Here, start command happened ${intent?.action}")
        intent?.let {
            val actionType =
                it.action?.run { RecordServiceActionType.valueOf(this) } ?: error("Missing action!")
            when (actionType) {
                RecordServiceActionType.ACTION_START_SERVICE -> {
                    val notificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val channel = NotificationChannel(
                        NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, IMPORTANCE_DEFAULT
                    )
                    notificationManager.createNotificationChannel(channel)
                    startForeground(NOTIFICATION_ID, notificationBuilder.build())
                    recordServiceStatesMutable.value = RecordServiceState.Climbing()
                    climbSessionDurationIncrementerJob = coroutineScope.launch {
                        while (isActive) {
                            (recordServiceStatesMutable.value as? RecordServiceState.Climbing)?.run {
                                val currMs = System.currentTimeMillis()
                                val newLength = currMs - climbingSession.startedOnUnixMs
                                val newClimbingSession = climbingSession.copy(lengthMs = newLength)
                                recordServiceStatesMutable.value = copy(
                                    climbingSession = newClimbingSession
                                )
                            }
                            delay(LOOP_DELAY_MILLISECONDS)
                        }
                    }
                }
                RecordServiceActionType.ACTION_STOP_SERVICE -> {
                    climbSessionDurationIncrementerJob?.cancel()
                    climbSessionDurationIncrementerJob = null
                    stopForeground(STOP_FOREGROUND_DETACH)
                    stopSelf()
                }
                RecordServiceActionType.ACTION_START_CLIMB -> {
                    val grade = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        it.getParcelableExtra(
                            ACTION_START_CLIMB_EXTRA_GRADE, Yosemite::class.java
                        )
                    } else {
                        it.getParcelableExtra(
                            ACTION_START_CLIMB_EXTRA_GRADE
                        )
                    } ?: error("Unknown grade!")
                    (recordServiceStatesMutable.value as? RecordServiceState.Climbing)?.run {
                        recordServiceStatesMutable.value = copy(
                            climbInProgress = Climb(
                                grade = grade,
                                lengthMs = ZERO_MILLISECONDS,
                                sent = false,
                                startedOnUnixMs = System.currentTimeMillis(),
                                trackPoints = listOf()
                            )
                        )
                    }
                    climbCurrentDurationIncrementerJob = coroutineScope.launch {
                        while (isActive) {
                            (recordServiceStatesMutable.value as? RecordServiceState.Climbing)?.run {
                                val newClimb = climbInProgress?.let { currClimb ->
                                    val currMs = System.currentTimeMillis()
                                    val newLength = currMs - currClimb.lengthMs
                                    currClimb.copy(lengthMs = newLength)
                                }
                                recordServiceStatesMutable.value = copy(climbInProgress = newClimb)
                            }
                            delay(LOOP_DELAY_MILLISECONDS)
                        }
                    }
                }
                RecordServiceActionType.ACTION_STOP_CLIMB_FELL -> {
                    climbCurrentDurationIncrementerJob?.cancel()
                    (recordServiceStatesMutable.value as RecordServiceState.Climbing).run {
                        recordServiceStatesMutable.value =
                            copy(climbInProgress = null, climbingSession = climbingSession.run {
                                copy(climbs = climbs.toMutableList().apply {
                                    climbInProgress?.let { newClimb ->
                                        add(
                                            newClimb.copy(
                                                sent = true
                                            )
                                        )
                                    }
                                })
                            })
                    }
                }
                RecordServiceActionType.ACTION_STOP_CLIMB_SENT -> {
                    climbCurrentDurationIncrementerJob?.cancel()
                    (recordServiceStatesMutable.value as RecordServiceState.Climbing).run {
                        recordServiceStatesMutable.value =
                            copy(climbInProgress = null, climbingSession = climbingSession.run {
                                copy(climbs = climbs.toMutableList().apply {
                                    climbInProgress?.let { newClimb ->
                                        add(
                                            newClimb.copy(
                                                sent = true
                                            )
                                        )
                                    }
                                })
                            })
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    companion object {
        private val recordServiceStatesMutable: MutableStateFlow<RecordServiceState> =
            MutableStateFlow(value = RecordServiceState.Standby)
        val recordServiceStates: StateFlow<RecordServiceState> = recordServiceStatesMutable
        const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
        const val ACTION_START_CLIMB = "ACTION_START_CLIMB"
        const val ACTION_STOP_CLIMB_FELL = "ACTION_STOP_CLIMB_FELL"
        const val ACTION_STOP_CLIMB_SENT = "ACTION_STOP_CLIMB_SENT"

        const val ACTION_START_CLIMB_EXTRA_GRADE = "ACTION_START_CLIMB_EXTRA_GRADE"
        const val NOTIFICATION_CHANNEL_ID = "ClimbingTracker Channel"
        const val NOTIFICATION_CHANNEL_NAME = "ClimbingTracker"
        const val NOTIFICATION_ID = 1

        private const val ZERO_MILLISECONDS = 0L
        private const val LOOP_DELAY_MILLISECONDS = 100L
    }


}
