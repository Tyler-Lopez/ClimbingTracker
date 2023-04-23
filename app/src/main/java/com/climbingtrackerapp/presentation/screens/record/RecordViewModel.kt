package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
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
        RecordViewState.Standby(
            isRecording = _isRecording,
            timeRecordedString = _timeRecordedString
        ).push()
    }

    override fun onEvent(event: RecordViewEvent) {
        when (event) {
            is RecordViewEvent.ToggledRecording -> onToggledRecording()
        }
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
