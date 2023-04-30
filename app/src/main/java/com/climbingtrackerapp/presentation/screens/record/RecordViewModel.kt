package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.domain.repository.RecordRepository
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.util.climbingGrade.ClimbingType
import com.climbingtrackerapp.util.climbingGrade.Yosemite
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val recordRepository: RecordRepository,
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
        RecordViewState.Standby(
            isRecording = _isRecording,
            timeRecordedString = recordRepository.recordedActivityLength
                .map { "00:00:00" } // todo, actually map
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Eagerly,
                    initialValue = "00:00:00"
                )
        ).push()
    }

    override fun onEvent(event: RecordViewEvent) {
        when (event) {
            is RecordViewEvent.ClickedAddClimb -> onClickedAddClimb()
            is RecordViewEvent.ClickedFell -> onClickedFell()
            is RecordViewEvent.ClickedSent -> onClickedSent()
            is RecordViewEvent.ToggledRecording -> onToggledRecording()
        }
    }

    private fun onClickedAddClimb() {
        MainDestination.NavigateSelectClimbingGrade(
            // todo
            climbingType = ClimbingType.INDOOR_TOP_ROPE
        ).push()
    }

    private fun onClickedFell() {

    }

    private fun onClickedSent() {

    }

    private fun onToggledRecording() {
        _isRecording.value = _isRecording.value.not()
        // Reset duration
        startedRecordingMs = null
        // Cancel previous recording
        jobRecording?.cancel()
        // If now recording, reset time and begin incrementing
        if (_isRecording.value) {
            startedRecordingMs = System.currentTimeMillis()
            jobRecording = viewModelScope.launch {
                while (isActive) {
                    startedRecordingMs?.let {
                        _timeRecordedString.value = (System.currentTimeMillis() - it)
                            .milliseconds
                            .format()
                    }
                    delay(DELAY_UPDATE)
                }
            }
        }
    }

    companion object {
        private val INITIAL_DURATION = 0.milliseconds
        private val DELAY_UPDATE = 10.milliseconds
        private const val DURATION_FORMATTER_STRING = "%02d:%02d:%02d"
    }
}
