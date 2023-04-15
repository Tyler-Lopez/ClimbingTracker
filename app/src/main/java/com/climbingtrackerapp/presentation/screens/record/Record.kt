package com.climbingtrackerapp.presentation.screens.record

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.StopCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ScalingLazyColumn
import com.climbingtrackerapp.architecture.EventReceiver
import java.time.format.DateTimeFormatter

@Composable
fun Record(viewModel: RecordViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is RecordViewState.Standby -> RecordStandby(state = this, eventReceiver = viewModel)
        }
    }
}

@Composable
private fun RecordStandby(
    state: RecordViewState.Standby,
    eventReceiver: EventReceiver<RecordViewEvent>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f, true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.timeRecordedString.value,
                    color = Color.White
                )
            }
        }
        IconButton(
            onClick = {
                eventReceiver.onEvent(RecordViewEvent.ToggledRecording)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (state.isRecording.value) {
                        Icons.Default.Stop
                    } else {
                        Icons.Default.PlayArrow
                    },
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }

}
