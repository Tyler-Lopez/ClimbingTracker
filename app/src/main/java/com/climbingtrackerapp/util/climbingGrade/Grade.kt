package com.climbingtrackerapp.util.climbingGrade

import androidx.annotation.IntRange

internal sealed interface Grade {
    val score: Int
}
