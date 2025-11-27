package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString

typealias IntGrid = Array<IntArray>

class DayNine(val input: String) {

    private fun String.toIntGrid() = lines().map { row ->
        row.map { it.digitToInt() }.toIntArray()
    }.toTypedArray()

    val caves: IntGrid = input.toIntGrid()

    fun IntGrid.findLowPoints(): List<Point> {
        return flatMapIndexed { r, row ->
            row.mapIndexed { c, height ->
                Point(c, r).takeIf { point ->
                    actualNeighbors(point)
                        .map {
                            caves[it]
                        }.all { height < it }
                }
            }.filterNotNull()
        }
    }

    fun getBasinSize(point: Point): Int {
        val visited = mutableSetOf(point)
        val queue = mutableListOf(point)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val neighbors = actualNeighbors(current)
                .filter { it !in visited }
                .filter { caves[it] != 9 }
            visited.addAll(neighbors)
            queue.addAll(neighbors)
        }
        return visited.size
    }

    fun actualNeighbors(point: Point): List<Point> =
        point.cardinalNeighbors().filter { it in caves }

    operator fun IntGrid.contains(point: Point): Boolean =
        point.r in this.indices && point.c in this[point.r].indices

    operator fun IntGrid.get(point: Point): Int = this[point.r][point.c]

    fun partOne(): Int {
        return caves.findLowPoints().sumOf {
            caves[it] + 1
        }
    }

    fun partTwo(): Int = caves
        .findLowPoints()
        .map { getBasinSize(it) }
        .sorted()
        .takeLast(3).reduce { acc, basin ->
            acc * basin
        }
}

fun main(args: Array<String>) {
    val input = fileAsString("day09_2021.txt")
    val solver = DayNine(input)
    println(solver.partOne())
    println(solver.partTwo())
}
