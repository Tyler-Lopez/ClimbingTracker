package com.climbingtrackerapp.domain.repository

import com.climbingtrackerapp.domain.model.Climb
import kotlin.time.Duration
import kotlinx.coroutines.flow.StateFlow

interface RecordRepository {
    val climbInProgress: StateFlow<Climb>
    val climbsCount: StateFlow<Int>

    fun startClimb(climb: Climb)
    fun startClimbSession()
    fun stopClimbInProgressFell()
    fun stopClimbInProgressSent()
    fun stopClimbSession()
}
