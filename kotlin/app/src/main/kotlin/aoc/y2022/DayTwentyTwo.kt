package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.toURI
import java.io.File
import java.lang.StringBuilder

class Board {
    var grid: MutableMap<Position, Char> = mutableMapOf()

    fun getMinXInRow(y: Int): Int = grid.keys.filter { p -> p.y == y }.map { it.x }.minOrNull() ?: 0
    fun getMaxXInRow(y: Int): Int = grid.keys.filter { p -> p.y == y }.map { it.x }.maxOrNull() ?: 0
    fun getMinYInCol(x: Int): Int = grid.keys.filter { p -> p.x == x }.map { it.y }.minOrNull() ?: 0
    fun getMaxYInCol(x: Int): Int = grid.keys.filter { p -> p.x == x }.map { it.y }.maxOrNull() ?: 0

    fun getEast(p: Position): Position = if (grid.contains(p.east())) p.east() else Position(getMinXInRow(y = p.y), p.y)
    fun getWest(p: Position): Position = if (grid.contains(p.west())) p.west() else Position(getMaxXInRow(y = p.y), p.y)
    fun getSouth(p: Position): Position =
        if (grid.contains(p.south())) p.south() else Position(p.x, getMinYInCol(x = p.x))

    fun getNorth(p: Position): Position =
        if (grid.contains(p.north())) p.north() else Position(p.x, getMaxYInCol(x = p.x))

    fun getNext(current: Player): Position {
        val next = when (current.facing) {
            Direction.NORTH -> getNorth(current.location)
            Direction.SOUTH -> getSouth(current.location)
            Direction.EAST -> getEast(current.location)
            Direction.WEST -> getWest(current.location)
        }
        return if (grid[next] == '.') next else current.location
    }

    fun move(current: Player, units: Int): Player {
        var position = current.location
        var steps = 0
        while (steps < units) {
            position = getNext(current)
            current.location = position
            steps++
        }
        return Player(position, current.facing)
    }

    fun turn(current: Player, dir: String): Player = Player(current.location, facing = current.facing.turn(to = dir))

    fun printBoard(player: Player): String {
        val positions = grid.keys
        val maxY = positions.map { it.y }.maxOrNull() ?: 0
        val maxX = positions.map { it.x }.maxOrNull() ?: 0

        val string = StringBuilder()
        string.append("\n")
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                val p = Position(x, y)
                if (player.location == p) {
                    when (player.facing) {
                        Direction.EAST -> string.append(">")
                        Direction.WEST -> string.append("<")
                        Direction.NORTH -> string.append("^")
                        Direction.SOUTH -> string.append("v")
                    }
                } else {
                    when {
                        grid.contains(p) -> string.append(grid[p])
                        else -> string.append(' ')
                    }
                }
            }
            string.append("\n")
        }
        return string.toString()
    }
}

data class Player(var location: Position, val facing: Direction)

class DayTwentyTwo(private val input: String) {
    val board = Board()
    var directions = ""

    init {
        val (map, directions) = input.split("\n\n")
        val rows = map.split("\n")
        val grid = mutableMapOf<Position, Char>()
        var y = 0
        for (row in rows) {
            var x = 0
            for (item in row) {
                if (item.isWhitespace()) {
                    x++
                } else {
                    grid[Position(x, y)] = item
                    x++
                }
            }
            y++
        }
        board.grid = grid
        this.directions = directions
    }

    val instructions: List<String> = directions
        .let { """\d+|[LR]""".toRegex().findAll(it) }
        .map { it.value }
        .toList()

    fun step(instruction: String, player: Player): Player {
        return when (instruction) {
            "R" -> board.turn(player, "R")
            "L" -> board.turn(player, "L")
            else -> board.move(
                current = player,
                units = instruction.toInt()
            )
        }
    }

    fun getPassword(player: Player): Int {
        val row = ((player.location.y + 1) * 1000)
        val col = ((player.location.x + 1) * 4)
        val facing = when (player.facing) {
            Direction.EAST -> 0
            Direction.SOUTH -> 1
            Direction.WEST -> 2
            Direction.NORTH -> 3
        }
        return row + col + facing
    }

    fun partOne(): Int {
        var player = Player(Position(board.getMinXInRow(y = 0), 0), Direction.EAST)
        for (step in instructions) {
            player = step(step, player)
        }
        return getPassword(player)
    }

    fun partTwo(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    val input = File("2022/day22_2022.txt".toURI()).readText()
    val solver = DayTwentyTwo(input)
    println(solver.partOne()) // 97356
}
