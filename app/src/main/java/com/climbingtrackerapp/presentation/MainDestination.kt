package com.climbingtrackerapp.presentation

import com.climbingtrackerapp.architecture.Destination
import com.climbingtrackerapp.util.climbingGrade.ClimbingType

sealed interface MainDestination : Destination {
    object NavigateSelectClimbingGrade : MainDestination
    data class NavigateRecord(val climbingType: ClimbingType) : MainDestination
}
