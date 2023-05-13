package com.climbingtrackerapp.presentation.screens.selectClimbingType

import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.domain.model.ClimbType
import javax.inject.Inject

class SelectClimbingTypeViewModel @Inject constructor(
    ssh: SavedStateHandle
) : BaseViewModel<SelectClimbingTypeViewState, SelectClimbingTypeViewEvent, MainDestination>() {

    init {
        SelectClimbingTypeViewState.Standby(
            types = ClimbType.values().toList()
        ).push()
    }

    override fun onEvent(event: SelectClimbingTypeViewEvent) {
        when (event) {
            is SelectClimbingTypeViewEvent.ClimbingTypeSelected -> onClimbingTypeSelected(event)
        }
    }

    private fun onClimbingTypeSelected(event: SelectClimbingTypeViewEvent.ClimbingTypeSelected) {
        MainDestination.NavigateRecord(event.climbingType).push()
    }
}
