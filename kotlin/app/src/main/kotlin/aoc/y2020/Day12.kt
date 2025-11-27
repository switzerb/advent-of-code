package aoc.y2020

import aoc.lib.Direction
import aoc.lib.Resources.fileAsList
import aoc.lib.Vector

data class Action(val type: Char, val units: Int)

data class Waypoint(val pos : Vector = Vector(10, 1)) {

    fun clockwise(deg: Int): Waypoint = Waypoint((0 until deg/90).fold(pos) { p, _ -> Vector(p.y(), -p.x())})

    fun move(dir: Direction, units: Int): Waypoint = Waypoint(pos.moveBy(dir, units))

}

data class Ship(val pos: Vector, val facing: Direction, val waypoint: Waypoint = Waypoint()) {

    private fun clockwise(deg: Int): Ship = Ship(pos, (0 until deg/90).fold(facing) { f, _ -> f.clockwise() })

    private fun forward(units: Int): Ship = move(facing, units)

    private fun move(dir: Direction, units: Int): Ship = Ship(pos.moveBy(dir, units), facing)

    fun run(action: Char, units: Int): Ship {
        return when (action) {
            'N' -> move(Direction.NORTH, units)
            'S' -> move(Direction.SOUTH, units)
            'E' -> move(Direction.EAST, units)
            'W' -> move(Direction.WEST, units)
            'L' -> clockwise(360 - units)
            'R' -> clockwise(units)
            'F' -> forward(units)
            else -> throw IllegalArgumentException("The action $action is not understood.")
        }
    }

    fun moveShipToWaypoint(units: Int): Vector =
        Vector(pos.x() + waypoint.pos.x() * units, pos.y() + waypoint.pos.y() * units)

    fun runWithWaypoint(action: Char, units: Int) : Ship {
        return when (action) {
            'N' -> Ship(pos, facing, waypoint.move(Direction.NORTH, units))
            'S' -> Ship(pos, facing, waypoint.move(Direction.SOUTH, units))
            'E' -> Ship(pos, facing, waypoint.move(Direction.EAST, units))
            'W' -> Ship(pos, facing, waypoint.move(Direction.WEST, units))
            'L' -> Ship(pos, facing, waypoint.clockwise( 360 - units))
            'R' -> Ship(pos, facing, waypoint.clockwise(units))
            'F' -> Ship(moveShipToWaypoint(units), facing, waypoint)
            else -> throw IllegalArgumentException("The action $action is not understood.")
        }
    }
}

class DayTwelve2020(input: List<String>) {

    private val actions: List<Action> = input.map { Action(it.first(), it.drop(1).toInt()) }

    fun partOne(): Int {
        val start = Ship(Vector.ORIGIN2D, Direction.EAST)
        val destination = actions.fold(start) { current, action ->
                current.run(action.type, action.units)
            }
        return Vector.ORIGIN2D.getManhattanDistance(destination.pos)
    }

    fun partTwo(): Int {
        val waypoint = Waypoint(Vector(10, 1))
        val start = Ship(Vector.ORIGIN2D, Direction.EAST, waypoint)
        val destination = actions.fold(start) { current, action ->
            current.runWithWaypoint(action.type, action.units)
        }
        return Vector.ORIGIN2D.getManhattanDistance(destination.pos)
    }
}

fun main(args: Array<String>) {
    val solver = DayTwelve2020(fileAsList("day12_2020.txt"))
    println(solver.partOne()) //2228
    println(solver.partTwo()) //42908
}
