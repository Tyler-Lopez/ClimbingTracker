package com.climbingtrackerapp.presentation.screens.record.subscreen

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.util.climbingGrade.Yosemite

sealed interface SelectClimbingGradeViewEvent : ViewEvent {
    data class ClickedGrade(val climbingGrade: Yosemite) : SelectClimbingGradeViewEvent
}
