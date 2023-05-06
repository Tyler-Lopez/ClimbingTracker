package com.climbingtrackerapp.domain.model

import kotlin.time.Duration

data class Climb(
    val duration: Duration,
    val grade: ClimbGrade,
    val sent: Boolean,
    val startedOnUnixMs: Long,
)
