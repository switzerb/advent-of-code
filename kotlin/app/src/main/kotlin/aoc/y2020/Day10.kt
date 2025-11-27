package aoc.y2020

import aoc.lib.Resources.fileAsInts

class DayTen2020(val input: List<Int>) {

    private val adapters = input.plus(0).plus(input.maxOrNull()!! + 3).sorted()

    private fun groupAdapters(): List<Int> {
        return adapters
            .zipWithNext()
            .map { it.first - it.second }
    }

    fun partOne(): Int {
        val groupedAdapters = adapters
            .zipWithNext()
            .map { it.second - it.first }
            .groupingBy { it }
            .eachCount()

        val ones = groupedAdapters[1]
        val threes = groupedAdapters[3]
        return ones!! * threes!!
    }

    fun partTwo(): Long {
        return groupAdapters()
            .joinToString("")
            .split("3")
            .map {
                when (it.length) {
                    1 -> 1
                    2 -> 2
                    3 -> 4
                    4 -> 7
                    else -> throw IllegalArgumentException("length not supported")
                }
            }
            .fold(1.toLong()) { a, n -> n * a }
    }
}

fun main(args: Array<String>) {
    val solver = DayTen2020(fileAsInts("day10_2020.txt"))
    println(solver.partOne()) // 2263
    println(solver.partTwo())
}
