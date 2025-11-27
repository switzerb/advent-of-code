package aoc.y2022

import aoc.lib.Resources.fileAsString

class DaySix(private val input: String) {

    fun partOne(): Int {
        val slices = input.windowed(size = 4, step = 1)
        var count = 3
        for (slice in slices) {
            val set = slice.toSet()
            if (set.size != 4) {
                count += 1
            } else {
                break
            }
        }
        return count + 1
    }

    fun partTwo(): Int {
        val slices = input.windowed(size = 14, step = 1)
        var count = 13
        for (slice in slices) {
            val set = slice.toSet()
            if (set.size != 14) {
                count += 1
            } else {
                break
            }
        }
        return count + 1
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day06_2022.txt")
    val solver = DaySix(input)
    println(solver.partOne())
    println(solver.partTwo())
}
