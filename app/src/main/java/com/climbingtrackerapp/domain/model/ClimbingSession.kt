package com.climbingtrackerapp.domain.model

data class ClimbingSession(
    val climbs: List<Climb>,
    val lengthMs: Long,
    val startedOnUnixMs: Long
)
