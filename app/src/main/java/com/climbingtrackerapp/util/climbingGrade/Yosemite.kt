package com.climbingtrackerapp.util.climbingGrade

sealed interface Yosemite {
    sealed interface Differentiation {
        enum class PlusMinusType : Differentiation { BASE, MINUS, PLUS }
        enum class AlphabetType : Differentiation { A, B, C, D }
    }

    val differentiation: Differentiation

    data class FiveFour(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveFive(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveSix(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveSeven(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveEight(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveNine(override val differentiation: Differentiation.PlusMinusType) : Yosemite
    data class FiveTen(override val differentiation: Differentiation.AlphabetType) : Yosemite
    data class FiveEleven(override val differentiation: Differentiation.AlphabetType) : Yosemite
    data class FiveTwelve(override val differentiation: Differentiation.AlphabetType) : Yosemite
    data class FiveThirteen(override val differentiation: Differentiation.AlphabetType) : Yosemite
    data class FiveFourteen(override val differentiation: Differentiation.AlphabetType) : Yosemite
    data class FiveFifteen(override val differentiation: Differentiation.AlphabetType) : Yosemite
}
