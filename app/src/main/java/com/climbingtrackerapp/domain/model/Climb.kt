package com.climbingtrackerapp.domain.model

import kotlin.time.Duration

// Consider this like a "Lap" in the TCX file
data class Climb(
    val grade: ClimbGrade,
    val lengthMs: Long,
    val sent: Boolean,
    val startedOnUnixMs: Long,
    val trackPoints: List<TrackPoint>
)
