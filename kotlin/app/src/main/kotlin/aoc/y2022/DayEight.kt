package aoc.y2022

import aoc.lib.Resources.fileAsList

typealias Grid = List<List<Int>>

enum class Dir {
    TOP,
    DOWN,
    RIGHT,
    LEFT
}

data class Point(val x: Int, val y: Int)

class DayEight(private val input: List<String>) {

    private val trees: Grid = buildList {
        for (row in input) {
            add(row.toList().map { it.digitToInt() })
        }
    }

    operator fun Grid.get(p: Point) =
        get(p.y)[p.x]

    private val Grid.height
        get() = size

    private val Grid.width
        get() = get(0).size

    private val Grid.edges: Int
        get() = (height * 2) + (width * 2) - 4

    fun lineOfSight(start: Point, dir: Dir): List<Point> {
        return when (dir) {
            Dir.TOP -> buildList {
                for (y in start.y - 1 downTo 0) {
                    add(Point(start.x, y))
                }
            }
            Dir.DOWN -> buildList {
                for (y in start.y + 1 until trees.height) {
                    add(Point(start.x, y))
                }
            }
            Dir.LEFT -> buildList {
                for (x in start.x - 1 downTo 0) {
                    add(Point(x, start.y))
                }
            }
            Dir.RIGHT -> buildList {
                for (x in start.x + 1 until trees.width) {
                    add(Point(x, start.y))
                }
            }
        }
    }

    fun isVisible(start: Point, dir: List<Point>): Boolean {
        val treeHeight = trees[start]
        for (p in dir) {
            if (treeHeight <= trees[p]) {
                return false
            }
        }
        return true
    }

    fun getScenicView(start: Point, dir: List<Point>): Int {
        val treeHeight = trees[start]
        var count = 0
        for (p in dir) {
            if (treeHeight <= trees[p]) {
                count++
                break
            }
            if (treeHeight > trees[p]) {
                count++
            } else {
                break
            }
        }
        return count
    }

    fun partOne(): Int {
        val visibleTrees: MutableList<Point> = mutableListOf()
        for (y in 1 until trees.height - 1) {
            for (x in 1 until trees.width - 1) {
                val start = Point(x, y)
                for (dir in Dir.values()) {
                    val isVisible = isVisible(start, lineOfSight(start, dir))
                    if (isVisible) {
                        visibleTrees.add(start)
                        break
                    }
                }
            }
        }
        return trees.edges + visibleTrees.size
    }

    fun partTwo(): Int? {
        val scenicScores = mutableListOf<Int>()
        for (y in 1 until trees.height - 1) {
            for (x in 1 until trees.width - 1) {
                val start = Point(x, y)
                var score = 1
                for (dir in Dir.values()) {
                    score *= getScenicView(start, lineOfSight(start, dir))
                }
                scenicScores.add(score)
            }
        }
        return scenicScores.maxOrNull()
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day08_2022.txt")
    val solver = DayEight(input)
    println(solver.partOne())
    println(solver.partTwo())
}
