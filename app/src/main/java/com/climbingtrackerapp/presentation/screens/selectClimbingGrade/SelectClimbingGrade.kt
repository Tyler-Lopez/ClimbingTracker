package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.*
import androidx.wear.compose.material.*
import com.climbingtrackerapp.architecture.EventReceiver
import com.climbingtrackerapp.util.climbingGrade.Yosemite

@Composable
fun SelectClimbingGrade(viewModel: SelectClimbingGradeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is SelectClimbingGradeViewState.Standby -> SelectClimbingGradeList(
                yosemiteList = yosemiteList, eventReceiver = viewModel
            )
        }
    }
}

@Composable
fun SelectClimbingGradeList(
    yosemiteList: List<Yosemite>, eventReceiver: EventReceiver<SelectClimbingGradeViewEvent>
) {
    ScalingLazyColumn(modifier = Modifier.fillMaxSize()) {
        items(yosemiteList.size) {
            val climb = yosemiteList[it]
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp), onClick = {
                eventReceiver.onEventDebounced(
                    SelectClimbingGradeViewEvent.ClickedClimbingGrade(
                        grade = climb
                    )
                )
            }) {
                Text(
                    text = climb.toString(withDifferentiation = true),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}
