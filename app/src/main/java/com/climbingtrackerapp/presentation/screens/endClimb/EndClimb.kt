package com.climbingtrackerapp.presentation.screens.endClimb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.climbingtrackerapp.presentation.screens.record.RecordViewEvent
import com.climbingtrackerapp.util.climbingGrade.Yosemite


@Composable
fun EndClimb(viewModel: EndClimbViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is EndClimbViewState.Standby -> EndClimbStandby()
        }
    }
}

@Composable
private fun EndClimbStandby() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = "00:00",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption3
        )
        Text(
            text = "End climbing session?",
            //   text = "${(state.climbGrade as Yosemite)!!.toString(true)} Climb in Progress",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption2
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 52.dp)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(weight = 1f, fill = true)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeft = 4.dp, bottomLeft = 4.dp
                        )
                    )
                    .background(Color(99, 24, 24))
                    .clickable {
                        //eventReceiver.onEvent(RecordViewEvent.ClickedFell)
                    }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "Cancel", style = MaterialTheme.typography.button
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width = 2.dp)
            )
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(weight = 1f, fill = true)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topRight = 4.dp, bottomRight = 4.dp
                        )
                    )
                    .background(Color(43, 99, 24))
                    .clickable {

                    }) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = "End", style = MaterialTheme.typography.button
                )
            }
        }
    }
}
