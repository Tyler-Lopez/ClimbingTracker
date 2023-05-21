package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import com.climbingtrackerapp.architecture.BaseViewModel
import com.climbingtrackerapp.presentation.MainDestination
import com.climbingtrackerapp.service.RecordService
import com.climbingtrackerapp.service.RecordServiceActionType
import com.climbingtrackerapp.util.climbingGrade.Yosemite
import com.climbingtrackerapp.domain.model.climbListFactory.ClimbGradeSpecificationListFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectClimbingGradeViewModel @Inject constructor(
    private val application: Application,
    climbSpecificationFactory: ClimbGradeSpecificationListFactory,
    ssh: SavedStateHandle
) : BaseViewModel<SelectClimbingGradeViewState, SelectClimbingGradeViewEvent, MainDestination>() {

    private val _yosemiteList: SnapshotStateList<Yosemite>

    init {
        _yosemiteList = climbSpecificationFactory
            .create(firstDecimal = 4, lastDecimal = 12)
            .toMutableStateList()
        SelectClimbingGradeViewState.Standby(yosemiteList = _yosemiteList).push()
    }

    override fun onEvent(event: SelectClimbingGradeViewEvent) {
        when (event) {
            is SelectClimbingGradeViewEvent.ClickedClimbingGrade -> onClickedClimbingGrade(event)
        }
    }

    private fun onClickedClimbingGrade(event: SelectClimbingGradeViewEvent.ClickedClimbingGrade) {
        sendCommandToRecordService(
            action = RecordServiceActionType.ACTION_START_CLIMB
        ) {
            putExtra(
                RecordService.ACTION_START_CLIMB_EXTRA_GRADE,
                event.grade
            )
        }
        MainDestination.NavigateUp.push()
    }

    private fun sendCommandToRecordService(
        action: RecordServiceActionType,
        intentScope: (Intent.() -> Unit)? = null
    ) {
        val context = application.applicationContext
        Intent(context, RecordService::class.java).apply {
            this.action = action.toString()
            intentScope?.invoke(this)
            context.startForegroundService(this)
        }
    }
}
