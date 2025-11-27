package aoc.y2022

import aoc.lib.Resources.fileAsString
import aoc.lib.Vector

/**
 * --- Day 18: Boiling Boulders ---
 * https://adventofcode.com/2022/day/18
 */

const val SIDES = 6

class DayEighteen(private val input: String) {

    private val cubes: Set<Vector> = input
        .split('\n')
        .map { coord ->
            Vector.from(coord)
        }.toSet()

    fun partOne(): Int {
        return cubes.map { cube ->
            SIDES - cube.neighbors()
                .count { neighbor -> neighbor in cubes }
        }.sum()
    }

    fun getRangeFrom(cubes: Set<Vector>, dimension: String): IntRange {
        return when (dimension) {
            "x" -> cubes.minOf { cube -> cube.x() } - 1..cubes.maxOf { cube -> cube.x() } + 1
            "y" -> cubes.minOf { cube -> cube.y() } - 1..cubes.maxOf { cube -> cube.y() } + 1
            "z" -> cubes.minOf { cube -> cube.z() } - 1..cubes.maxOf { cube -> cube.z() } + 1
            else -> throw Error("You are entering the fourth dimension")
        }
    }

    fun partTwo(): Int {
        val xRange = getRangeFrom(cubes, "x")
        val yRange = getRangeFrom(cubes, "y")
        val zRange = getRangeFrom(cubes, "z")

        val queue = ArrayDeque<Vector>()
        queue.add(Vector(xRange.first, yRange.first, zRange.first))

        val visited = mutableSetOf<Vector>()
        var sidesFound = 0

        queue.forEach { next ->
            if (next !in visited) {
                next.neighbors()
                    .filter { it.x() in xRange && it.y() in yRange && it.z() in zRange }
                    .forEach { neighbor ->
                        visited += next
                        if (neighbor in cubes) sidesFound++
                        else queue.add(neighbor)
                    }
            }
        }
        return sidesFound
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day18_2022.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
    println(solver.partTwo())
}
