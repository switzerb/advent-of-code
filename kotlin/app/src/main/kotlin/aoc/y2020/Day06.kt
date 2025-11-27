package aoc.y2020

import aoc.lib.getInput

class DaySix2020(val input: String) {

    fun partOne(): Int {
        return input
            .trim()
            .split("\n\n".toRegex())
            .asSequence()
            .map { it.replace("\n", "") }
            .map { it.toSet() }
            .map { it.joinToString("") }
            .map { it.length }
            .sum()
    }

    private fun histogram(answers: List<String>): Int {
        val hist = mutableMapOf<Char, Int>()
        answers.forEach { person ->
            person.forEach {
                hist[it] = hist.getOrDefault(it, 0) + 1
            }
        }
        return hist
            .filterValues { it == answers.size }
            .size
    }

    fun partTwo(): Int {
        return input
            .trim()
            .split("\n\n".toRegex())
            .map { it.split("\n") }
            .map { histogram(it) }
            .sum()
    }
}

fun main(args: Array<String>) {
    val input = getInput<DaySix2020>("day6_2020.txt")
    val solver = DaySix2020(input)
    println(solver.partOne()) // 7110
    println(solver.partTwo()) // 3628
}
