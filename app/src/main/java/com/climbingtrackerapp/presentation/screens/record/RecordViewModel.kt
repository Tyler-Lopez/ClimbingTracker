package com.climbingtrackerapp.presentation.screens.record

import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewEvent
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewState
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    ssh: SavedStateHandle
) : BaseViewModel<RecordViewState, RecordViewEvent, MainDestination>() {
    override fun onEvent(event: RecordViewEvent) {
        TODO("Not yet implemented")
    }
}
