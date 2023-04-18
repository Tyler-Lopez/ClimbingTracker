package com.climbingtrackerapp.presentation.screens.selectClimbingType

import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewEvent
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewState
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactory
import javax.inject.Inject

class SelectClimbingTypeViewModel @Inject constructor(
    ssh: SavedStateHandle
) : BaseViewModel<SelectClimbingTypeViewState, SelectClimbingTypeViewEvent, MainDestination>() {
    override fun onEvent(event: SelectClimbingTypeViewEvent) {
        TODO("Not yet implemented")
    }
}
