package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString
import kotlin.math.abs

data class Target(val xRange: IntRange, val yRange: IntRange) {
    val xMax = xRange.last
    val yMax = yRange.first
}

data class Probe(val position: Point, val velocity: Point) {
    private fun drag(x: Int): Int = if (x == 0) 0 else x - 1
    private fun gravity(y: Int): Int = y - 1

    fun next(): Probe = Probe(
        position = Point(position.x + velocity.x, position.y + velocity.y),
        velocity = Point(drag(velocity.x), gravity(velocity.y))
    )
}

class DaySeventeen(private val input: String) {

    private val target = input.parse()

    private fun inTargetArea(current: Probe): Boolean =
        current.position.x in target.xRange &&
            current.position.y in target.yRange

    fun launch(initial: Point): Point? {
        var current = Probe(Point.ORIGIN, initial)
        while (true) {
            current = current.next()
            if (inTargetArea(current)) {
                return initial
            }
            if (current.position.x > target.xMax || current.position.y < target.yMax) {
                return null
            }
        }
    }

    fun partOne(): Int = (abs(target.yMax) - 1 downTo 0).sum()

    fun partTwo(): Int {
        val potentials = mutableListOf<Point>()
        (abs(target.yMax) downTo target.yMax).map { y ->
            (0..target.xMax).map { x ->
                val result = launch(Point(x, y))
                if (result != null) {
                    potentials.add(result)
                }
            }
        }
        return potentials.size
    }

    private fun String.parse(): Target {
        val (x, y) = this.split(",").map { coord ->
            val (min, max) = coord
                .trim()
                .drop(2)
                .split("..").map { n ->
                    n.toInt()
                }
            IntRange(min, max)
        }
        return Target(xRange = x, yRange = y)
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day17_2021.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne()) // 2278
    println(solver.partTwo()) // 996
}
