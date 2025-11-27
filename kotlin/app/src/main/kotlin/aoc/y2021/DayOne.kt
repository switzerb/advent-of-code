package aoc.y2021
import aoc.lib.Resources.fileAsList

class DayOne(private val input: List<String>) {

    fun partOne(): Int {
        return input
            .zipWithNext()
            .map { (it.first).toInt() - (it.second).toInt() }
            .filter { n -> n < 0 }
            .size
    }

    fun partTwo(): Int {
        return input
            .map { it.toInt() }
            .windowed(size = 3, step = 1)
            .zipWithNext()
            .map { it.first.sum() - it.second.sum() }
            .filter { n -> n < 0 }
            .size
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("day01_2021.txt")
    val solver = DayOne(input)
    println("Part One: " + solver.partOne())
    println("Part Two: " + solver.partTwo())
}
