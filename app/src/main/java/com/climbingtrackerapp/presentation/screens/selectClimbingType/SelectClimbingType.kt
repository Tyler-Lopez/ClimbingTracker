package com.climbingtrackerapp.presentation.screens.selectClimbingType

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items

@Composable
fun SelectClimbingType(viewModel: SelectClimbingTypeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is SelectClimbingTypeViewState.Standby -> {
                ScalingLazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(types) {
                        Button(
                            onClick = {}
                        ) {
                            Text(
                                text = it.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}
