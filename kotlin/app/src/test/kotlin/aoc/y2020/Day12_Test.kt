package aoc.y2020

import aoc.lib.Direction
import aoc.lib.Vector
import org.junit.Test
import kotlin.test.assertEquals

class DayTwelve2020Test {

    val input = listOf("F10","N3","F7","R90","F11")
    val solver = DayTwelve2020(input)

    @Test
    fun ex1() { assertEquals(25, solver.partOne()) }

    @Test
    fun ex2() { assertEquals(286, solver.partTwo()) }

    @Test
    fun waypointRotation() {
        val waypoint = Waypoint(Vector(2,3))
        val ninety = Waypoint(Vector(3,-2))
        val oneEighty = Waypoint(Vector(-2,-3))
        assertEquals(ninety, waypoint.clockwise(90))
        assertEquals(oneEighty, waypoint.clockwise(180))
    }
    @Test
    fun waypointRotation2() {
        val waypoint = Waypoint(Vector(-5,-1))
        val ninety = Waypoint(Vector(-1,5))
        val oneEighty = Waypoint(Vector(5,1))
        assertEquals(ninety, waypoint.clockwise(90))
        assertEquals(oneEighty, waypoint.clockwise(180))
    }

    @Test
    fun moveShipToWaypoint() {
        val ship = Ship(Vector.ORIGIN2D, Direction.EAST, Waypoint())
        assertEquals(Vector(100,10),ship.moveShipToWaypoint(10))
    }

    @Test
    fun moveShipToWaypoint2() {
        val ship = Ship(Vector(170,38),Direction.EAST, Waypoint(Vector(4,-10)))
        assertEquals(Vector(214,-72), ship.moveShipToWaypoint(11))
    }

}
