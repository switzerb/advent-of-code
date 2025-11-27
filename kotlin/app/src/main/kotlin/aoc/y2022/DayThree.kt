package aoc.y2022

import aoc.lib.Resources.fileAsList

/**
 * --- Day 3: Rucksack Reorganization ---
 * https://adventofcode.com/2022/day/3
 */
class DayThree(private val input: List<String>) {

    private fun Char.withPriority(): Int = if (this.isLowerCase()) this.code - 96 else this.code - 38

    fun partOne(): Int {
        return input.map { items ->
            val itemLength = items.length / 2
            val (c1, c2) = items.chunked(itemLength).map(String::toSet)
            val both = c1.intersect(c2)
            both.first().withPriority()
        }.sum()
    }

    fun partTwo(): Int {
        return input.chunked(3)
            .map { group ->
                val (elf1, elf2, elf3) = group.map(String::toSet)
                val all = elf1.intersect(elf2.intersect(elf3))
                all.first().withPriority()
            }.sum()
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day03_2022.txt")
    val solver = DayThree(input)
    println(solver.partOne())
    println(solver.partTwo())
}
