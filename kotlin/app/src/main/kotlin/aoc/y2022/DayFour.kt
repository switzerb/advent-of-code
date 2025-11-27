package aoc.y2022

import aoc.lib.Resources.fileAsList

/**
 * --- Day 4: Camp Cleanup ---
 * https://adventofcode.com/2022/day/4
 */
class DayFour(private val input: List<String>) {

    fun List<String>.parse(): List<List<Pair<Int, Int>>> {
        return this.map { elves ->
            elves.split(",").map { elf ->
                val something = elf.split("-")
                Pair(something[0].toInt(), something[1].toInt())
            }
        }
    }

    fun isFullyContained(elves: List<Pair<Int, Int>>): Boolean {
        val (elf1, elf2) = elves
        if (elf1.first <= elf2.first && elf1.second >= elf2.second) {
            return true
        }
        if (elf2.first <= elf1.first && elf2.second >= elf1.second) {
            return true
        }
        return false
    }

    fun hasOverlap(elves: List<Pair<Int, Int>>): Boolean {
        val (elf1, elf2) = elves
        val r1 = (elf1.first..elf1.second)
        if (elf2.first in r1 || elf2.second in r1) {
            return true
        }
        val r2 = (elf2.first..elf2.second)
        if (elf1.first in r2 || elf1.second in r2) {
            return true
        }
        return false
    }

    fun partOne(): Int {
        return input.parse().map {
            if (isFullyContained(it)) 1 else 0
        }.sum()
    }

    fun partTwo(): Int {
        return input.parse().map {
            if (hasOverlap(it)) 1 else 0
        }.sum()
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day04_2022.txt")
    val solver = DayFour(input)
    println(solver.partOne())
    println(solver.partTwo())
}
