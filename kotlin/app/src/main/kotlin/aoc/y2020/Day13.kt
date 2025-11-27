package aoc.y2020

import aoc.lib.Resources.fileAsString

class DayThirteen2020(val timestamp: Int, val input: List<String>) {

    fun partOne(): Int? {
        val schedule = input.filter { it != "x" }.map { it.toInt() }.sorted()
        val whatever = schedule
            .map { it - (timestamp % it) }
            .zip(schedule)
            .minByOrNull { it.first }
        return whatever?.first?.times(whatever.second)
    }

    fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

    fun partTwo(): Int {
        val schedule = input.filter { it != "x" }.map { it.toInt() }
        val pattern = listOf(0,1,4,6,7)
        val lcm = lcm(7,13)
        println(schedule)
        println(pattern)
        println(lcm)
        return 0
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day13_2020.txt").trim().split(",")
    val solver = DayThirteen2020(1011416, input)
    println(solver.partOne()) //4135
}
