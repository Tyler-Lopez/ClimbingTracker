package com.climbingtrackerapp.service

import com.climbingtrackerapp.domain.model.Climb
import com.climbingtrackerapp.domain.model.ClimbingSession
import kotlin.time.Duration
import kotlinx.coroutines.flow.StateFlow

sealed interface RecordServiceState {
    data class Climbing(
        val climbInProgress: Climb? = null,
        val climbingSession: ClimbingSession = ClimbingSession(
            climbs = listOf(),
            lengthMs = 0L,
            startedOnUnixMs = System.currentTimeMillis()
        )
    ) : RecordServiceState

    object Standby : RecordServiceState
}
