package com.climbingtrackerapp.util.climbingGrade

import androidx.annotation.IntRange

internal sealed interface Grade {
    val score: Int

    data class Boulder(@IntRange(0, 107) override val score: Int) : Grade
    data class Route(@IntRange(0, 107) override val score: Int) : Grade
}
