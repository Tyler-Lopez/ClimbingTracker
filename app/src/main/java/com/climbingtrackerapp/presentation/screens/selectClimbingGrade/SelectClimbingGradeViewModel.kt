package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectClimbingGradeViewModel @Inject constructor(ssh: SavedStateHandle) :
    BaseViewModel<SelectClimbingGradeViewState, SelectClimbingGradeViewEvent, MainDestination>() {

    override fun onEvent(event: SelectClimbingGradeViewEvent) {
        TODO("Not yet implemented")
    }
}
