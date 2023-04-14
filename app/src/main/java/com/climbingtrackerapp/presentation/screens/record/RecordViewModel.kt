package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    private val _isRecording: MutableState<Boolean> = mutableStateOf(value = false)

    init {
        RecordViewState.Standby(
            isRecording = _isRecording
        ).push()
    }

    override fun onEvent(event: RecordViewEvent) {
        when (event) {
            is RecordViewEvent.ToggledRecording -> onToggledRecording()
        }
    }

    private fun onToggledRecording() {
        _isRecording.value = _isRecording.value.not()
    }
}
