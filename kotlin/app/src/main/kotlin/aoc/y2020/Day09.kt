package aoc.y2020

import aoc.lib.Resources.fileAsLongs

class DayNine2020(val input: List<Long>, private val window: Int) {

    private var preamble = window

    fun partOne(): Long =
        input.windowed(preamble + 1, 1, false).first { !it.preambleIsValid() }.last()

    fun partTwo(): Long {
        val target = partOne()
        val range = input.takeWhile { it != target }.findSums(target)
        return range.minOrNull()!! + range.maxOrNull()!!
    }

    private fun List<Long>.preambleIsValid(): Boolean {
        val target = this.last()
        val subjects = this.dropLast(1).toSet()
        return subjects.any { target - it in subjects }
    }

    private fun List<Long>.findSums(target: Long): List<Long> =
        this.indices.mapNotNull { start ->
            this.indices.drop(start + 1).reversed().mapNotNull { end ->
                this.subList(start, end).takeIf { it.sum() == target }
            }.firstOrNull()
        }.first()
}

fun main(args: Array<String>) {
    val input = fileAsLongs("day9_2020.txt")
    val solver = DayNine2020(input, 25)
    println(solver.partOne()) // 69316178
    println(solver.partTwo()) // 9351526
}
