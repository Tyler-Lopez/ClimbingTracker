package com.climbingtrackerapp.domain.model

import kotlin.time.Duration

data class ClimbingSession(
    val climbs: List<Climb>,
    val lengthMs: Long,
    val startedOnUnixMs: Long
)
