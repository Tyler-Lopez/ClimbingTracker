package com.climbingtrackerapp.domain.model

// Consider this like a "Lap" in the TCX file
data class Climb(
    val climbScore: Int,
    val climbType: String,
    val lengthMs: Long,
    val sent: Boolean,
    val startedOnUnixMs: Long,
    val trackPoints: List<TrackPoint>
)
