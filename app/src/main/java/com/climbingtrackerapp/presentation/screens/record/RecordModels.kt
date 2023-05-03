package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.State
import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import com.climbingtrackerapp.domain.model.ClimbGrade
import kotlinx.coroutines.flow.StateFlow

sealed interface RecordViewState : ViewState {
    data class Climbing(
        val climbGrade: ClimbGrade,
       // val timeRecordedString: StateFlow<String>
    ) : RecordViewState

    data class Standby(
        val climbCount: Int
    ) : RecordViewState
}

sealed interface RecordViewEvent : ViewEvent {
    object ClickedAddClimb : RecordViewEvent
    object ClickedFell : RecordViewEvent
    object ClickedSent : RecordViewEvent
    object ToggledRecording : RecordViewEvent
}
