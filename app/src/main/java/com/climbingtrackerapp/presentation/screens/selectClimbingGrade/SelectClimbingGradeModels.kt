package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.architecture.ViewState
import com.climbingtrackerapp.util.climbingGrade.Yosemite

sealed interface SelectClimbingGradeViewState : ViewState {
    data class Standby(
        val yosemiteList: List<Yosemite>
    ) : SelectClimbingGradeViewState
}

sealed interface SelectClimbingGradeViewEvent : ViewEvent
