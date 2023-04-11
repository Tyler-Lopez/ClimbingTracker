package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.util.climbingGrade.Yosemite
import com.climbingtrackerapp.util.climbingGrade.YosemiteListFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectClimbingGradeViewModel @Inject constructor(
    yosemiteListFactory: YosemiteListFactory,
    ssh: SavedStateHandle
) : BaseViewModel<SelectClimbingGradeViewState, SelectClimbingGradeViewEvent, MainDestination>() {

    private val _yosemiteList: SnapshotStateList<Yosemite>

    init {
        _yosemiteList = yosemiteListFactory
            .create(firstDecimal = 4, lastDecimal = 12)
            .toMutableStateList()
        SelectClimbingGradeViewState.Standby(yosemiteList = _yosemiteList).push()
    }

    override fun onEvent(event: SelectClimbingGradeViewEvent) {
        TODO("Not yet implemented")
    }
}
