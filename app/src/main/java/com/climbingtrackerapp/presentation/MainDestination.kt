package com.climbingtrackerapp.presentation

import com.climbingtrackerapp.architecture.Destination
import com.climbingtrackerapp.domain.model.ClimbType

sealed interface MainDestination : Destination {
    object NavigateSelectClimbingType : MainDestination
    object NavigateEndClimb : MainDestination
    data class NavigateSelectClimbingGrade(val climbingType: ClimbType) : MainDestination
    data class NavigateRecord(val climbingType: ClimbType) : MainDestination
    object NavigateUp : MainDestination
}
