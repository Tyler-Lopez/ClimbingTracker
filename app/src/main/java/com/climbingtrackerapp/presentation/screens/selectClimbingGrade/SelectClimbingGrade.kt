package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.climbingtrackerapp.util.climbingGrade.Yosemite

@Composable
fun SelectClimbingGrade(viewModel: SelectClimbingGradeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is SelectClimbingGradeViewState.Standby -> SelectClimbingGradeList(
                yosemiteList = yosemiteList
            )
        }
    }
}

@Composable
fun SelectClimbingGradeList(yosemiteList: List<Yosemite>) {
    ScalingLazyColumn {
        println("here")
        items(yosemiteList.size) {
            println("yo here again $it")
            Card(onClick = { /*TODO*/ }) {
                Text("Hello $it", color = Color.White)
            }
        }
    }
}
