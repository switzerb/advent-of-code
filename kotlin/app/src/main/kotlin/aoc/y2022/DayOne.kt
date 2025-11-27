package aoc.y2022

import aoc.lib.Resources.fileAsString

/*
--- Day 1: Calorie Counting ---
https://adventofcode.com/2022/day/1
 */
class DayOne(private val input: String) {
    val sums = mutableListOf<Int>()

    fun partOne(): Int? {
        input.split("\n\n").map { elf ->
            val calories = elf
                .split("\n")
                .map { it.toInt() }
                .sum()
            sums.add(calories)
        }
        return sums.maxOrNull()
    }

    fun partTwo(): Int = sums.sortedDescending().take(3).sum()
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day01_2022.txt")
    val solver = DayOne(input)
    println(solver.partOne())
    println(solver.partTwo())
}
