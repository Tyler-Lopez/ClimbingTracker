package com.climbingtrackerapp.presentation.screens.endClimb

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState

sealed interface EndClimbViewState : ViewState {
    object Standby : EndClimbViewState
}

sealed interface EndClimbViewEvent : ViewEvent {
    object ClickedCancel : EndClimbViewEvent
    object ClickedConfirmEndClimb : EndClimbViewEvent
}
