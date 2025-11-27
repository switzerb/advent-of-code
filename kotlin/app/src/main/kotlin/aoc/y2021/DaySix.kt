package aoc.y2021

import aoc.lib.Resources.fileAsString

class DaySix(input: String) {
    val lanternfish: LongArray = parseInput(input)

    fun partOne(): Long =
        run(80)

    fun partTwo(): Long =
        run(256)

    private fun run(days: Int): Long {
        (1..days).forEach {
            progressGeneration()
        }
        return lanternfish.sum()
    }

    // array index is day
    // count how many fish are at that point in the cycle
    // progress fish through the array by moving them into
    // a different bucket
    private fun progressGeneration() {
        val first = lanternfish.first()
        lanternfish.copyInto(lanternfish, startIndex = 1)
        lanternfish[8] = first
        lanternfish[6] += lanternfish[8]
    }

    private fun parseInput(input: String): LongArray =
        LongArray(9).apply {
            input
                .split(",")
                .map { it.toInt() }
                .forEach { this[it] += 1L }
        }
}

fun main(args: Array<String>) {
    val input = fileAsString("day06_2021.txt")
    val solver = DaySix(input)
//    println(solver.partOne()) //390011
    println(solver.partTwo()) //1852978735751314 NOPE
}
