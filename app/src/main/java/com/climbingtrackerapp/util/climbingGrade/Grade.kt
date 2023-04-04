package com.climbingtrackerapp.util.climbingGrade

import androidx.annotation.IntRange

sealed interface Grade {
    val score: Int

    data class Boulder(@IntRange(from = 0, to = 107) override val score: Int) : Grade {

        val vScale: String
        val fontScale: String

        companion object {
            fun fromVScale(value: String): Boulder {

            }

            fun fromFontScale(value: String): Boulder {

            }
        }
    }

    data class Route(@IntRange(from = 0, to = 107) override val score: Int) : Grade {

        val yosemiteDecimalSystem: String
        val frenchSystem: String

        companion object {
            fun fromYosemiteDecimalSystem(value: String): Route {

            }

            fun fromFrenchSystem(value: String): Route {

            }
        }
    }
}
