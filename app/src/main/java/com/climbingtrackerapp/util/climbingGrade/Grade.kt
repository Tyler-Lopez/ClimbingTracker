package com.climbingtrackerapp.util.climbingGrade

import androidx.annotation.IntRange
import com.climbingtrackerapp.util.enums.GradeDifferentiateType

sealed interface Grade {
    val gradeDifferentiateType: GradeDifferentiateType
    val score: Int

    data class BoulderGrade(
        override val gradeDifferentiateType: GradeDifferentiateType,
        @IntRange(from = 0L, to = 22L) override val score: Int
    ) : Grade

    data class RouteGrade(
        override val gradeDifferentiateType: GradeDifferentiateType,
        @IntRange(from = 0L, to = 15L) override val score: Int,
    ) : Grade
}
