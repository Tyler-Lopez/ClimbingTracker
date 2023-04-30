package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun Record(viewModel: RecordViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is RecordViewState.Standby -> RecordStandby(
                state = this,
                eventReceiver = viewModel
            )
            is RecordViewState.Climbing -> RecordClimbing(
                state = this,
                eventReceiver = viewModel
            )
        }
    }
}
