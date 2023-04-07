package com.climbingtrackerapp.util.climbingGrade

interface YosemiteListFactory {
    fun create(firstDecimal: Int, lastDecimal: Int): List<Yosemite>
}
