package aoc.y2022

import aoc.lib.Resources.fileAsList

/**
 * --- Day 2: Rock Paper Scissors ---
 * https://adventofcode.com/2022/day/2
 */
class DayTwo(private val input: List<String>) {

    private val playResult: Map<String, Int> = mapOf(
        "A X" to 3 + 1,
        "A Y" to 6 + 2,
        "A Z" to 0 + 3,
        "B X" to 0 + 1,
        "B Y" to 3 + 2,
        "B Z" to 6 + 3,
        "C X" to 6 + 1,
        "C Y" to 0 + 2,
        "C Z" to 3 + 3
    )

    private val playResultWithStrategy: Map<String, Int> = mapOf(
        "A X" to 0 + 3,
        "A Y" to 3 + 1,
        "A Z" to 6 + 2,
        "B X" to 0 + 1,
        "B Y" to 3 + 2,
        "B Z" to 6 + 3,
        "C X" to 0 + 2,
        "C Y" to 3 + 3,
        "C Z" to 6 + 1
    )

    fun partOne(): Int = input.map {
        playResult.getOrDefault(it, 0)
    }.sum()

    fun partTwo(): Int = input.map {
        playResultWithStrategy.getOrDefault(it, 0)
    }.sum()
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day02_2022.txt")
    val solver = DayTwo(input)
    println(solver.partOne()) // 8392
    println(solver.partTwo()) // 10116
}
