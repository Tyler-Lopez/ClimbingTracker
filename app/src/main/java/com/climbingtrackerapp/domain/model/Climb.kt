package com.climbingtrackerapp.domain.model

import java.time.Duration

data class Climb(
    val duration: Duration,
    val grade: ClimbGrade
)
