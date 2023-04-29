package com.climbingtrackerapp.domain.model

import java.time.Duration

interface ClimbingSession {
    val duration: Duration
    val climbs: List<Climb>
}
