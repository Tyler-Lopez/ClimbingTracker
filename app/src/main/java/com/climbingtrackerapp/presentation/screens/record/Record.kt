package com.climbingtrackerapp.presentation.screens.record

import android.graphics.Paint.Align
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.climbingtrackerapp.architecture.EventReceiver
import java.time.format.DateTimeFormatter

@Composable
fun Record(
    swipeToDismissBoxState: SwipeToDismissBoxState,
    viewModel: RecordViewModel
) {
    viewModel.viewState.collectAsState().value?.apply {
        when (this) {
            is RecordViewState.Standby -> RecordStandby(
                state = this,
                swipeToDismissBoxState = swipeToDismissBoxState,
                eventReceiver = viewModel
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecordStandby(
    state: RecordViewState.Standby,
    swipeToDismissBoxState: SwipeToDismissBoxState,
    eventReceiver: EventReceiver<RecordViewEvent>
) {
    val pagerState = rememberPagerState()
    // Box {
    Box(
        modifier = Modifier.edgeSwipeToDismiss(
            swipeToDismissBoxState = swipeToDismissBoxState,
            edgeWidth = 90.dp
        )
    ) {
        HorizontalPager(
            pageCount = 2,
            state = pagerState
        ) {
            Text(
                text = "$it Current page is $it",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    //  }
    /*
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = state.timeRecordedString.value,
            color = MaterialTheme.colors.onBackground
        )
        IconButton(
            onClick = {
                eventReceiver.onEvent(RecordViewEvent.ToggledRecording)
            }, modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
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

     */
}
