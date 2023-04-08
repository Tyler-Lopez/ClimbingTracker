package com.climbingtrackerapp.presentation.screens.selectClimbingGrade

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.wear.compose.material.Text

@Composable
fun SelectClimbingGrade(viewModel: SelectClimbingGradeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {

    }
}
