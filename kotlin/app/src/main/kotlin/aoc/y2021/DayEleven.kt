package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString

typealias OctopusConsortium = Map<Point, Int>

class DayEleven(private val input: String) {

    val octopii: OctopusConsortium = input.toGrid()

    fun OctopusConsortium.step(): Sequence<Int> = sequence {
        val octopii = this@step.toMutableMap()

        while (true) {
            octopii.forEach { point, energy ->
                octopii[point] = energy + 1
            }

            do {
                val flashed = octopii.filterValues { it > 9 }.keys
                flashed.forEach { point -> octopii[point] = 0 }
                flashed.flatMap { point ->
                    point.allNeighbors()
                }.filter { point ->
                    point in octopii && octopii[point] != 0
                }.forEach { point ->
                    octopii[point] = octopii.getValue(point) + 1
                }
            } while (flashed.isNotEmpty())
            yield(octopii.count { it.value == 0 })
        }
    }

    fun partOne(): Int {
        return octopii.step().take(100).sum()
    }

    fun partTwo(): Int {
        return octopii.step().indexOfFirst { it == octopii.size } + 1
    }

    private fun String.toGrid(): Map<Point, Int> {
        return lines().flatMapIndexed { y, row ->
            row.mapIndexed { x, energy -> Point(x, y) to energy.digitToInt() }
        }.toMap()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day11_2021.txt")
    val solver = DayEleven(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
