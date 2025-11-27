package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

class Cave(
    val rocks: MutableSet<Position>,
    val sandAtRest: MutableSet<Position> = mutableSetOf(),
    var falling: Position? = null
) {

    fun maxX(): Int = rocks.map { it.x }.maxOrNull() ?: 0
    fun maxY(): Int = rocks.map { it.y }.maxOrNull() ?: 0
    fun minX(): Int = rocks.map { it.x }.minOrNull() ?: 0
    fun minY(): Int = rocks.map { it.y }.minOrNull() ?: 0

    override fun toString(): String {
        val string = StringBuilder()
        (minY() - 10..maxY() + 5).map { y ->
            (minX() - 20..maxX() + 10).map { x ->
                if (rocks.contains(Position(x, y))) {
                    string.append("#")
                } else if (sandAtRest.contains(Position(x, y))) {
                    string.append('o')
                } else if (falling == Position(x, y)) {
                    string.append('+')
                } else {
                    string.append(".")
                }
            }
            string.append('\n')
        }
        return string.toString()
    }
}

class DayFourteen(private val input: String) {

    fun parsed(): MutableSet<Position> = input
        .split("\n")
        .flatMap { row ->
            row.split(" -> ")
                .map { coord -> Position.from(coord) }
                .zipWithNext()
                .flatMap { (start, end) ->
                    start.lineTo(end)
                }
        }.toMutableSet()

    val cave = Cave(rocks = parsed())

    fun canMoveLeft(sand: Position): Boolean {
        val next = Position(sand.x - 1, sand.y + 1)
        if (cave.rocks.contains(next) || cave.sandAtRest.contains(next)) {
            return false
        }
        return true
    }

    fun canMoveRight(sand: Position): Boolean {
        val next = Position(sand.x + 1, sand.y + 1)
        if (cave.rocks.contains(next) || cave.sandAtRest.contains(next)) {
            return false
        }
        return true
    }

    fun canMoveDown(sand: Position): Boolean {
        val next = Position(sand.x, sand.y + 1)
        if (
            cave.rocks.contains(next) ||
            cave.sandAtRest.contains(next)
        ) {
            return false
        }
        return true
    }

    fun canMove(sand: Position): Boolean {
        return canMoveDown(sand) ||
            canMoveLeft(sand) ||
            canMoveRight(sand)
    }

    fun move(sand: Position): Position {
        val down = sand.moveBy(Direction.SOUTH, 1)
        if (canMoveDown(sand)) {
            return down
        }
        if (canMoveLeft(sand)) {
            return down.moveBy(Direction.EAST, 1)
        }
        if (canMoveRight(sand)) {
            return down.moveBy(Direction.WEST, 1)
        }
        throw UnsupportedOperationException("we should never get here")
    }

    fun partOne(): Int {
        val origin = Position(500, 0)
        var current = origin
        var void = false
        while (!void) {
            while (canMove(current) && !void) {
                cave.falling = move(current)
                current = cave.falling!!
                if (current.y > cave.maxY()) {
                    void = true
                }
            }
            cave.sandAtRest.add(current)
//            println(cave)
            current = origin
        }
        return cave.sandAtRest.size - 1
    }

    fun partTwo(): Int {
        cave.rocks.addAll(
            Position(
                cave.minX() - cave.maxY(),
                cave.maxY() + 2
            ).lineTo(Position(cave.maxX() + cave.maxY(), cave.maxY() + 2))
        )
        val origin = Position(500, 0)
        var current = origin
        var void = false
        while (!void) {
            if (cave.sandAtRest.contains(origin)) {
                void = true
            }
            while (canMove(current)) {
                cave.falling = move(current)
                current = cave.falling!!
            }
            cave.sandAtRest.add(current)
            current = origin
        }
        return cave.sandAtRest.size
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day14_2022.txt")
    val solver = DayFourteen(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
