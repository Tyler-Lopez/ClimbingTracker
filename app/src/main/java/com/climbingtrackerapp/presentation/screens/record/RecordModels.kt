package com.climbingtrackerapp.presentation.screens.record

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import com.climbingtrackerapp.domain.model.climbSpecification.ClimbSpecification

sealed interface RecordViewState : ViewState {
    data class Climbing(
        val climbSpecification: ClimbSpecification
        // val timeRecordedString: StateFlow<String>
    ) : RecordViewState

    data class Standby(
        val climbingSessionLength: String,
        val climbCount: Int
    ) : RecordViewState
}

sealed interface RecordViewEvent : ViewEvent {
    object ClickedAddClimb : RecordViewEvent
    object ClickedEndClimbSession : RecordViewEvent
    object ClickedFell : RecordViewEvent
    object ClickedSent : RecordViewEvent
}
