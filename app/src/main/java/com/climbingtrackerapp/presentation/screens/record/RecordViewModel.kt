package com.climbingtrackerapp.presentation.screens.record

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.service.RecordService
import com.climbingtrackerapp.service.RecordServiceActionType
import com.climbingtrackerapp.service.RecordServiceState
import com.climbingtrackerapp.domain.model.ClimbType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val application: Application,
    ssh: SavedStateHandle
) : BaseViewModel<RecordViewState, RecordViewEvent, MainDestination>() {

    private var jobRecording: Job? = null
    private var startedRecordingMs: Long? = null

    /** States observed in the view **/
    private val _isRecording: MutableState<Boolean> = mutableStateOf(value = true)
    private val _timeRecordedString: MutableState<String> = mutableStateOf(
        value = INITIAL_DURATION.format()
    )

    private fun Duration.format(): String {
        val seconds = inWholeSeconds
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secondsInMinute = seconds % 60
        return String.format(DURATION_FORMATTER_STRING, hours, minutes, secondsInMinute)
    }

    init {
        /*
        RecordViewState.Climbing(
            Yosemite.FiveSeven(Yosemite.Differentiation.PlusMinusType.BASE)
        ).push()
        */
        viewModelScope.launch {
            RecordService.recordServiceStates.collect {
                println("Here, trying to collect... collected is $it")
                when (it) {
                    is RecordServiceState.Climbing -> {
                        if (it.climbInProgress != null) {
                            RecordViewState.Climbing(
                                climbGrade = it.climbInProgress.grade,
                            ).push()
                        } else {
                            RecordViewState.Standby(
                                climbingSessionLength = it
                                    .climbingSession
                                    .lengthMs
                                    .milliseconds
                                    .format(),
                                climbCount = it.climbingSession.climbs.count()
                            ).push()
                        }
                    }
                    is RecordServiceState.Standby -> {
                        sendCommandToRecordService(
                            action = RecordServiceActionType.ACTION_START_SERVICE
                        )
                    }
                }
            }
        }
    }

    override fun onEvent(event: RecordViewEvent) {
        when (event) {
            is RecordViewEvent.ClickedAddClimb -> onClickedAddClimb()
            is RecordViewEvent.ClickedEndClimbSession -> onClickedEndClimbSession()
            is RecordViewEvent.ClickedFell -> onClickedFell()
            is RecordViewEvent.ClickedSent -> onClickedSent()
        }
    }

    private fun onClickedAddClimb() {
        MainDestination.NavigateSelectClimbingGrade(
            // todo
            climbingType = ClimbType.INDOOR_TOP_ROPE
        ).push()
    }

    private fun onClickedEndClimbSession() {
        MainDestination.NavigateEndClimb.push()
    }

    private fun onClickedFell() {
        sendCommandToRecordService(
            action = RecordServiceActionType.ACTION_STOP_CLIMB_FELL
        )
    }

    private fun onClickedSent() {
        sendCommandToRecordService(
            action = RecordServiceActionType.ACTION_STOP_CLIMB_SENT
        )
    }

    private fun sendCommandToRecordService(
        action: RecordServiceActionType,
        intentScope: (Intent.() -> Unit)? = null
    ) {
        val context = application.applicationContext
        Intent(context, RecordService::class.java).apply {
            this.action = action.toString()
            intentScope?.invoke(this)
            context.startForegroundService(this)
        }
    }

    companion object {
        private val INITIAL_DURATION = 0.milliseconds
        private val DELAY_UPDATE = 10.milliseconds
        private const val DURATION_FORMATTER_STRING = "%02d:%02d:%02d"
    }
}
