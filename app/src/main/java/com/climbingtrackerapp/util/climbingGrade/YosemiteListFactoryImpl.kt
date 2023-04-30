package com.climbingtrackerapp.util.climbingGrade

class YosemiteListFactoryImpl : YosemiteListFactory {
    override fun create(firstDecimal: Int, lastDecimal: Int): List<Yosemite> {
        require(firstDecimal <= lastDecimal)
        require(firstDecimal >= DECIMAL_FIRST)
        require(lastDecimal <= DECIMAL_LAST)
        return (firstDecimal..lastDecimal).flatMap {
            mutableListOf<Yosemite>().apply {
                when (it) {
                    4 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveFour(differentiation = it))
                    }
                    5 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveFive(differentiation = it))
                    }
                    6 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveSix(differentiation = it))
                    }
                    7 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveSeven(differentiation = it))
                    }
                    8 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveEight(differentiation = it))
                    }
                    9 -> Yosemite.Differentiation.PlusMinusType.values().forEach {
                        add(Yosemite.FiveNine(differentiation = it))
                    }
                    10 -> Yosemite.FiveTen(differentiation = DEFAULT_ALPHABET_TYPE)
                    11 -> Yosemite.FiveEleven(differentiation = DEFAULT_ALPHABET_TYPE)
                    12 -> Yosemite.FiveTwelve(differentiation = DEFAULT_ALPHABET_TYPE)
                    13 -> Yosemite.FiveThirteen(differentiation = DEFAULT_ALPHABET_TYPE)
                    14 -> Yosemite.FiveFourteen(differentiation = DEFAULT_ALPHABET_TYPE)
                    else -> error("Invalid decimal value $it")
                }
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
