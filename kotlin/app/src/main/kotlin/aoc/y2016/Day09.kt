package aoc.y2016

import aoc.lib.Resources

/*
--- Day 9: Explosives in Cyberspace ---
https://adventofcode.com/2016/day/9
*/

class Day09(private val input: String) {

    fun partOne(): Int {
        var runningLength = input.length
        var current = 0

        while (true) {
            val idx_open = input.indexOf('(', current)
            if (idx_open == -1) {
                break
            }
            val idx_close = input.indexOf(')', idx_open)

            val marker = input.substring(idx_open + 1, idx_close)

            // get the numbers that we need to operate with
            val (charLen, reps) = marker.split('x').map { it.toInt() }

            // add the total length of the chars that we are adding
            runningLength += (charLen * reps)
            // subtract the length of the marker, plus the following characters, plus the parens
            runningLength -= (marker.length + charLen + 2)
            current += idx_close + charLen + 1
        }
        return runningLength
    }
}

fun main(args: Array<String>) {
    val input = Resources.fileAsString("2016/input_day09.txt")
    val solver = Day09(input)

    // 26332 - incorrect, too low
    println(solver.partOne())
}
