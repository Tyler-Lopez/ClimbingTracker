package com.climbingtrackerapp.domain.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.climbingtrackerapp.domain.repository.RecordRepository
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecordService @Inject constructor(
    private val recordRepository: RecordRepository,
    private val notificationBuilder: NotificationCompat.Builder
) : LifecycleService() {

    private val _recordedActivityLength: MutableStateFlow<Duration> = MutableStateFlow(value = ZERO)
    private val _isTracking: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    private var serviceAlive: Boolean = false

    val recordedActivityLength: StateFlow<Duration> = _recordedActivityLength

    init {
      //  recordedActivityRepository.reco
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply {
            when (action) {
                ACTION_RESUME -> serviceStart()
                ACTION_PAUSE -> servicePause()
                ACTION_STOP -> serviceStop()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun servicePause() {
        _isTracking.value = false
    }

    private fun serviceStart() {
        if (!serviceAlive) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)

            startForeground(NOTIFICATION_ID, notificationBuilder.build())
            serviceAlive = true
        }
        _isTracking.value = true
        CoroutineScope(Dispatchers.Main).launch {
            while (_isTracking.value) {
                delay(duration = TIMER_UPDATE_INTERVAL)
            }
        }
    }

    private fun serviceStop() {
        serviceAlive = false
        _isTracking.value = false
        stopForeground(STOP_FOREGROUND_DETACH)
        stopSelf()
    }

    companion object {
        const val ACTION_RESUME = "ACTION_RESUME"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_STOP = "ACTION_STOP"

        const val NOTIFICATION_CHANNEL_ID = "Tracking Channel"
        const val NOTIFICATION_CHANNEL_NAME = "Tracking"
        const val NOTIFICATION_ID = 1

        private val TIMER_UPDATE_INTERVAL = 1.seconds
    }
}
