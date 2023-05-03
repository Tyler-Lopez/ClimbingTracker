package com.climbingtrackerapp.util.climbingGrade

import android.os.Parcelable
import com.climbingtrackerapp.domain.model.ClimbGrade
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
sealed class Yosemite : ClimbGrade, Parcelable {
    sealed interface Differentiation {
        enum class PlusMinusType : Differentiation { MINUS, BASE, PLUS }
        enum class AlphabetType : Differentiation { A, B, C, D }
    }

    abstract val differentiation: Differentiation

    data class FiveFour(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveFive(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveSix(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveSeven(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveEight(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveNine(override val differentiation: Differentiation.PlusMinusType) : Yosemite()
    data class FiveTen(override val differentiation: Differentiation.AlphabetType) : Yosemite()
    data class FiveEleven(override val differentiation: Differentiation.AlphabetType) : Yosemite()
    data class FiveTwelve(override val differentiation: Differentiation.AlphabetType) : Yosemite()
    data class FiveThirteen(override val differentiation: Differentiation.AlphabetType) : Yosemite()
    data class FiveFourteen(override val differentiation: Differentiation.AlphabetType) : Yosemite()
    data class FiveFifteen(override val differentiation: Differentiation.AlphabetType) : Yosemite()

    fun toString(withDifferentiation: Boolean): String {
        return buildString {
            append("5.")
            append(
                when (this@Yosemite) {
                    is FiveFour -> 4
                    is FiveFive -> 5
                    is FiveSix -> 6
                    is FiveSeven -> 7
                    is FiveEight -> 8
                    is FiveNine -> 9
                    is FiveTen -> 10
                    is FiveEleven -> 11
                    is FiveTwelve -> 12
                    is FiveThirteen -> 13
                    is FiveFourteen -> 14
                    is FiveFifteen -> 15
                }
            )
            if (withDifferentiation) {
                val differentiation: Char? = when (differentiation) {
                    Differentiation.AlphabetType.A -> 'a'
                    Differentiation.AlphabetType.B -> 'b'
                    Differentiation.AlphabetType.C -> 'c'
                    Differentiation.AlphabetType.D -> 'd'
                    Differentiation.PlusMinusType.BASE -> null
                    Differentiation.PlusMinusType.MINUS -> '-'
                    Differentiation.PlusMinusType.PLUS -> '+'
                }
                differentiation?.let { append(it) }
            }
        }
    }
}
