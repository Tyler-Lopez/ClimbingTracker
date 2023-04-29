package com.climbingtrackerapp.presentation

import com.climbingtrackerapp.architecture.Destination
import com.climbingtrackerapp.util.climbingGrade.ClimbingType

sealed interface MainDestination : Destination {
    data class NavigateSelectClimbingGrade(val climbingType: ClimbingType) : MainDestination
    data class NavigateRecord(val climbingType: ClimbingType) : MainDestination
    object NavigateUp : MainDestination
}
