package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.climbingtrackerapp.architecture.EventReceiver

@Composable
fun Record(viewModel: RecordViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is RecordViewState.Standby -> RecordStandby(
                state = this,
                eventReceiver = viewModel
            )
        }
    }
}

@Composable
private fun RecordStandby(
    state: RecordViewState.Standby,
    eventReceiver: EventReceiver<RecordViewEvent>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = state.timeRecordedString.collectAsState().value,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 72.dp)
                .padding(all = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeft = 4.dp,
                            bottomLeft = 4.dp
                        )
                    )
                    .background(MaterialTheme.colors.primary)
                    .clickable { eventReceiver.onEvent(RecordViewEvent.ToggledRecording) }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = if (state.isRecording.value) {
                        Icons.Outlined.Pause
                    } else {
                        Icons.Outlined.PlayArrow
                    },
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 2.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 2.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(weight = 1f, fill = true)
                    .fillMaxHeight()
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topRight = 4.dp,
                            bottomRight = 4.dp
                        )
                    )
                    .background(MaterialTheme.colors.primary)
                    .clickable { }
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = if (state.isRecording.value) {
                        "Add Climb"
                    } else {
                        "End Activity"
                    },
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}
