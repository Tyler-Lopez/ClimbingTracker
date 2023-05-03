package com.climbingtrackerapp.service

import com.climbingtrackerapp.domain.model.Climb
import kotlinx.coroutines.flow.StateFlow

sealed interface RecordServiceState {
    data class Climbing(
        val climbInProgress: Climb? = null,
        val climbs: List<Climb> = listOf()
    ) : RecordServiceState
    object Standby : RecordServiceState
}
