package com.climbingtrackerapp.util.climbingGrade

class YosemiteListFactoryImpl : YosemiteListFactory {
    override fun create(firstDecimal: Int, lastDecimal: Int): List<Yosemite> {
        require(firstDecimal <= lastDecimal)
        require(firstDecimal >= DECIMAL_FIRST)
        require(lastDecimal <= DECIMAL_LAST)
        return (firstDecimal..lastDecimal).map {
            when (it) {
                4 -> Yosemite.FiveFour(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                5 -> Yosemite.FiveFive(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                6 -> Yosemite.FiveSix(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                7 -> Yosemite.FiveSeven(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                8 -> Yosemite.FiveEight(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                9 -> Yosemite.FiveNine(differentiation = DEFAULT_PLUS_MINUS_TYPE)
                10 -> Yosemite.FiveTen(differentiation = DEFAULT_ALPHABET_TYPE)
                11 -> Yosemite.FiveEleven(differentiation = DEFAULT_ALPHABET_TYPE)
                12 -> Yosemite.FiveTwelve(differentiation = DEFAULT_ALPHABET_TYPE)
                13 -> Yosemite.FiveThirteen(differentiation = DEFAULT_ALPHABET_TYPE)
                14 -> Yosemite.FiveFourteen(differentiation = DEFAULT_ALPHABET_TYPE)
                else -> error("Invalid decimal value $it")
            }
        }
    }

    companion object {
        private const val DECIMAL_FIRST = 4
        private const val DECIMAL_LAST = 14

        private val DEFAULT_PLUS_MINUS_TYPE = Yosemite.Differentiation.PlusMinusType.BASE
        private val DEFAULT_ALPHABET_TYPE = Yosemite.Differentiation.AlphabetType.A
    }
}
