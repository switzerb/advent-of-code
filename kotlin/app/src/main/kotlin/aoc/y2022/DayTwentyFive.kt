package aoc.y2022

import aoc.lib.Resources.fileAsString
import kotlin.math.pow

class DayTwentyFive(private val input: String) {

    val BASE: Double = 5.0

    val numbers = input
        .split("\n")

    fun toSnafu(n: Long): String {
        val snafuLookup = listOf("0", "1", "2", "=", "-")
        return generateSequence(n) { (it + 2) / 5 }
            .takeWhile { it != 0L }
            .map { snafuLookup[(it % 5).toInt()] }
            .joinToString("")
            .reversed()
    }

    fun fromSnafu(s: String): Long {
        val places = s.toCharArray().reversed()
        return places.mapIndexed { idx, char ->
            val placeValue = BASE.pow(idx)
            when (char) {
                '0' -> (0 * placeValue).toLong()
                '1' -> (1 * placeValue).toLong()
                '2' -> (2 * placeValue).toLong()
                '=' -> (-2 * placeValue).toLong()
                '-' -> (-1 * placeValue).toLong()
                else -> throw UnsupportedOperationException("$char not recognized")
            }
        }.sum()
    }

    fun partOne(): String {
        val decimal = numbers.sumOf { fromSnafu(it) }
        return toSnafu(decimal)
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day25_2022.txt")
    val solver = DayTwentyFive(input)
    println(solver.partOne())
}
