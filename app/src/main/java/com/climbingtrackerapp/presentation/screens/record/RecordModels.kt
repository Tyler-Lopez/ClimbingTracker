package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.State
import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState

sealed interface RecordViewState : ViewState {
    data class Standby(val isRecording: State<Boolean>) : RecordViewState
}
sealed interface RecordViewEvent : ViewEvent {
    object ToggledRecording : RecordViewEvent
}
