package com.climbingtrackerapp.presentation.screens.selectClimbingType

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewEvent
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewState
import com.climbingtrackerapp.util.climbingGrade.ClimbingType

sealed interface SelectClimbingTypeViewState : ViewState {
    data class Standby(val types: List<ClimbingType>) : SelectClimbingTypeViewState
}

sealed interface SelectClimbingTypeViewEvent : ViewEvent {
    data class ClimbingTypeSelected(val climbingType: ClimbingType) : SelectClimbingTypeViewEvent
}
