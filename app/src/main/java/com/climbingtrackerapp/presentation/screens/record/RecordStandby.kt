package com.climbingtrackerapp.presentation.screens.record

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StopCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.climbingtrackerapp.architecture.EventReceiver


@Composable
fun RecordStandby(
    state: RecordViewState.Standby,
    eventReceiver: EventReceiver<RecordViewEvent>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = state.climbingSessionLength,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption2
        )
        Text(
            text = "Indoor Top Rope",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption2
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 40.dp)
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
                    .clickable { eventReceiver.onEvent(RecordViewEvent.ClickedEndClimbSession) }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Outlined.StopCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
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
                    .clickable {
                        eventReceiver.onEventDebounced(
                            event = RecordViewEvent.ClickedAddClimb
                        )
                    }
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Add Climb",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button
                )
            }
        }
        Text(
            text = "${state.climbCount} climbs in this activity",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption3
        )
    }
}
