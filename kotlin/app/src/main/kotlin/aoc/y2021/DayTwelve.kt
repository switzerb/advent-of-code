package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayTwelve(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day12_2021.txt")
    val solver = DayTwelve(input)
    println(solver.partOne())
}
