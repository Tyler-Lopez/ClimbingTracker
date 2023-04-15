package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.State
import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import kotlin.time.Duration

sealed interface RecordViewState : ViewState {
    data class Standby(
        val isRecording: State<Boolean>,
        val timeRecordedString: State<String>
    ) : RecordViewState
}

sealed interface RecordViewEvent : ViewEvent {
    object ToggledRecording : RecordViewEvent
}
