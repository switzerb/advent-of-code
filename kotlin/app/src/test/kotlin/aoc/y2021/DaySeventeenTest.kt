package aoc.y2021

import aoc.lib.Point
import org.junit.Test
import kotlin.test.assertEquals

class DaySeventeenTest {

    val input = "x=20..30, y=-10..-5"

    val solver = DaySeventeen(input)

    @Test
    fun testP1Ex1() {
        assertEquals(45, solver.partOne())
    }

    @Test
    fun testP2Ex1() {
        assertEquals(112, solver.partTwo())
    }

    @Test
    fun testLaunch() {
        assertEquals(Point(23, -10), solver.launch(Point(23, -10)))
    }
}
