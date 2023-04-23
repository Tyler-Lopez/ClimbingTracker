package com.climbingtrackerapp.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface RecordedActivityRepository {
    fun recordReset()
    fun recordIncrementTimeBy(additionalMs:  Long)
    val recordedActivityFlow: StateFlow<Long>
}
