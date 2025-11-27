package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString
import java.util.PriorityQueue

class Path(val point: Point, val risk: Int) : Comparable<Path> {
    override fun compareTo(other: Path): Int =
        this.risk - other.risk
}

class DayFifteen(input: String) {
    private val cavern: Array<IntArray> = input.toIntGrid()
    private val firstRow = cavern.first()
    private val width = cavern.first().size
    private val end = Point(firstRow.lastIndex, cavern.lastIndex)

    fun partOne(): Int? {
        return cavern.run(end)
    }

    fun partTwo(): Int? {
        return cavern.run(
            Point((firstRow.size * 5) - 1, (cavern.size * 5) - 1)
        )
    }

    private fun Array<IntArray>.run(
        end: Point
    ): Int? {
        val queue = PriorityQueue<Path>()
        queue.add(Path(Point.ORIGIN, 0))
        val visited = mutableSetOf<Point>()

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.point == end) {
                return current.risk
            }
            if (current.point !in visited) {
                visited.add(current.point)
                current.point
                    .cardinalNeighbors()
                    .filter { (it.x in (0..end.x)) && (it.y in (0..end.y)) }
                    .forEach { queue.add(Path(it, current.risk + getRisk(it))) }
            }
        }
        return null
    }

    private fun getRisk(point: Point): Int {
        val risk = cavern[point.y % cavern.size][point.x % width]
        val nextRisk = (risk + (point.x / width) + (point.y / cavern.size))
        return if (nextRisk < 10) {
            nextRisk
        } else {
            nextRisk - 9
        }
    }

    private fun String.toIntGrid(): Array<IntArray> =
        lines().map { row ->
            row.map { risk ->
                risk.digitToInt()
            }.toIntArray()
        }.toTypedArray()
}

fun main(args: Array<String>) {
    val input = fileAsString("day15_2021.txt")
    val solver = DayFifteen(input)
    println(solver.partOne()) // 592
    println(solver.partTwo()) // 2897
}
