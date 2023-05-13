package com.climbingtrackerapp.presentation.screens.selectClimbingType

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import com.climbingtrackerapp.domain.model.ClimbType

sealed interface SelectClimbingTypeViewState : ViewState {
    data class Standby(val types: List<ClimbType>) : SelectClimbingTypeViewState
}

sealed interface SelectClimbingTypeViewEvent : ViewEvent {
    data class ClimbingTypeSelected(val climbingType: ClimbType) : SelectClimbingTypeViewEvent
}
