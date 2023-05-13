package com.climbingtrackerapp.presentation.screens.endClimb

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.presentation.screens.record.RecordViewEvent
import com.climbingtrackerapp.presentation.screens.record.RecordViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EndClimbViewModel @Inject constructor(
    ssh: SavedStateHandle
) : BaseViewModel<EndClimbViewState, EndClimbViewEvent, MainDestination>() {

    init {
        EndClimbViewState.Standby.push()
    }

    override fun onEvent(event: EndClimbViewEvent) {
        TODO("Not yet implemented")
    }
}
