package com.climbingtrackerapp.presentation.screens.record.subscreen

import com.climbingtrackerapp.architecture.ViewEvent
import com.climbingtrackerapp.presentation.screens.record.RecordViewEvent

fun interface SelectClimbingGradeViewEventListener {
    fun onEvent(event: SelectClimbingGradeViewEvent)
}
