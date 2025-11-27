package aoc.y2021
import aoc.lib.Resources.fileAsList
import aoc.lib.Vector
import java.lang.Math.abs

class DayFive(private val input: List<String>) {

    fun parse(): List<Vector> {
        return input
            .flatMap { line ->
                line.split(" -> ").map { point ->
                    val coords = point.split(",")
                    Vector(coords.first().toInt(), coords.last().toInt())
                }
            }
            .windowed(2, 2)
            .flatMap { line ->
                expandLines(line.first(), line.last())
            }
    }

    fun expandLines(p1: Vector, p2: Vector): List<Vector> {
        val x1 = p1.x()
        val x2 = p2.x()
        val y1 = p1.y()
        val y2 = p2.y()

        when {
            x1 == x2 -> {
                return (minOf(y1, y2)..maxOf(y1, y2))
                    .map {
                        Vector(x1, it)
                    }
            }
            y1 == y2 -> {
                return (minOf(x1, x2)..maxOf(x1, x2))
                    .map {
                        Vector(it, y1)
                    }
            }
            else -> {
                // slope is either 1 or -1
                val minX = minOf(x1, x2)
                val maxX = maxOf(x1, x2)
                val minY = minOf(y1, y2)
                val maxY = maxOf(y1, y2)

                val distance = abs(x1 - x2)

                return (0..distance).map { idx ->
                    when {
                        (x1 < x2 && y1 < y2) -> Vector(minX + idx, minY + idx)
                        (x1 > x2 && y1 < y2) -> Vector(maxX - idx, minY + idx)
                        (x1 < x2 && y1 > y2) -> Vector(minX + idx, maxY - idx)
                        (x1 > x2 && y1 > y2) -> Vector(maxX - idx, maxY - idx)
                        else -> throw Error("This should be possible")
                    }
                }
            }
        }
    }

    fun partOne(): Int {
        val vents = parse()
        val hist = mutableMapOf<Vector, Int>()

        vents.forEach { point ->
            hist[point] = hist.getOrDefault(point, 0) + 1
        }
        return hist.filterValues { count ->
            count >= 2
        }.size
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsList("day05_2021.txt")
    val solver = DayFive(input)
    println(solver.partOne())
}
