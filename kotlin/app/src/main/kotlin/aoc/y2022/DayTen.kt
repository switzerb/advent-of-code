package aoc.y2022

import aoc.lib.Resources.fileAsList

typealias Action = Pair<String, Int>

class DayTen(private val input: List<String>) {

    private val CRT_WIDTH = 40
    private val CYCLE_MARK = listOf(20, 60, 100, 140, 180, 220)

    private val instructions = buildList {
        for (instr in input) {
            val info = instr.split(" ")
            when (info.first()) {
                "addx" -> {
                    add(Action("noop", 0))
                    add(Action(info.first(), info.last().toInt()))
                }
                "noop" -> add(Pair(info.first(), 0))
            }
        }
    }

    fun step(X: Int, action: Action): Int = when (action.first) {
        "noop" -> X
        "addx" -> X + action.second
        else -> throw Error("Operation not supported")
    }

    fun partOne(): Int {
        val signals = mutableListOf<Int>()
        var X = 1
        for (cycle in 1..240) {
            if (cycle in CYCLE_MARK) {
                signals.add(cycle * X)
            }
            val action = instructions[cycle - 1]
            X = step(X, action)
        }
        return signals.sum()
    }

    fun partTwo() = buildString {
        var X = 1
        var pixelBit = 0

        for (cycle in 1..240) {
            val sprite = listOf(X - 1, X, X + 1)
            if (pixelBit in sprite) {
                append("#")
            } else {
                append(".")
            }
            val action = instructions[cycle - 1]
            X = step(X, action)
            pixelBit++
            if (cycle % CRT_WIDTH == 0) {
                append("\n")
                pixelBit = 0
            }
        }
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day10_2022.txt")
    val solver = DayTen(input)
    println(solver.partOne())
    println(solver.partTwo())
}
