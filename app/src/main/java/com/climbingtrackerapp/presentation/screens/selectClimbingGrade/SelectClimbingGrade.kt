package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.climbingtrackerapp.architecture.EventReceiver
import com.climbingtrackerapp.util.climbingGrade.Yosemite

@Composable
fun SelectClimbingGrade(viewModel: SelectClimbingGradeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is SelectClimbingGradeViewState.Standby -> SelectClimbingGradeList(
                yosemiteList = yosemiteList,
                eventReceiver = viewModel
            )
        }
    }
}

@Composable
fun SelectClimbingGradeList(
    yosemiteList: List<Yosemite>,
    eventReceiver: EventReceiver<SelectClimbingGradeViewEvent>
) {
    ScalingLazyColumn {
        println("here")
        items(yosemiteList.size) {
            val climb = yosemiteList[it]
            Card(onClick = {
                println("here, on clicked")
                val climbingGrade = yosemiteList[it]
                eventReceiver.onEventDebounced(
                    SelectClimbingGradeViewEvent.ClickedClimbingGrade(
                        grade = climbingGrade
                    )
                )
            }) {
                Text(
                    text = climb.toString(),
                    color = Color.White
                )
            }
        }
    }
}
