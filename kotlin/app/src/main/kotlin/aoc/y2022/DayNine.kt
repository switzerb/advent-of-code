package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.fileAsList

typealias Movement = Pair<Direction, Int>

class DayNine(private val input: List<String>) {

    private val movements: List<Movement> = input.map { movement ->
        val (dir, amount) = movement.split(" ")
        Movement(Direction.from(dir), amount.toInt())
    }
    val start = Position.ORIGIN

    fun calculateNext(head: Position, next: Position): Position {
        // if we are still touching head, don't move
        if (next in head.nextToInclusive()) {
            return next
        } else {
            return when {
                head.isNorthOf(next) -> Position(next.x, next.y - 1)
                head.isSouthOf(next) -> Position(next.x, next.y + 1)
                head.isEastOf(next) -> Position(next.x - 1, next.y)
                head.isWestOf(next) -> Position(next.x + 1, next.y)
                head.isNorthWestOf(next) -> Position(next.x + 1, next.y - 1)
                head.isSouthWestOf(next) -> Position(next.x + 1, next.y + 1)
                head.isSouthEastOf(next) -> Position(next.x - 1, next.y + 1)
                head.isNorthEastOf(next) -> Position(next.x - 1, next.y - 1)
                else -> next
            }
        }
    }

    fun partOne(): Int {
        val locations: MutableSet<Position> = mutableSetOf()
        var head = start
        var tail = start
        for (movement in movements) {
            for (i in 1..movement.second) {
                head = head.moveBy(dir = movement.first)
                tail = calculateNext(
                    head = head,
                    next = tail
                )
                locations.add(tail)
            }
        }
        return locations.size
    }

    fun partTwo(): Int {
        val locations: MutableSet<Position> = mutableSetOf(start)
        val knots = mutableListOf<Position>(
            start, // head
            start, // 1
            start, // 2
            start, // 3
            start, // 4
            start, // 5
            start, // 6
            start, // 7
            start, // 8
            start // 9 (tail)
        )
        for (movement in movements) {
            for (i in 1..movement.second) {
                knots[0] = knots[0].moveBy(dir = movement.first)
                for (k in knots.indices) {
                    if (k > 0) {
                        val prev = knots[k - 1]
                        knots[k] = calculateNext(
                            head = prev,
                            next = knots[k]
                        )
                    }
                }
                locations.add(knots[9])
            }
        }
        return locations.size
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day09_2022.txt")
    val solver = DayNine(input)
    println(solver.partOne())
    println(solver.partTwo())
}
