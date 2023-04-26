package com.climbingtrackerapp.domain.repository

import kotlin.time.Duration
import kotlinx.coroutines.flow.StateFlow

interface RecordRepository {
    /** When true, any service listening to this repository should start, otherwise it should be
     * stopped. **/
    val isRecording: StateFlow<Boolean>

    /** The length of the recorded activity **/
    val recordedActivityLength: StateFlow<Duration>

    fun updateActivityLength(newLength: Duration)
}
