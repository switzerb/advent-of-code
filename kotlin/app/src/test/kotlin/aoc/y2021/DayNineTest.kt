package aoc.y2021

import aoc.lib.Point
import org.junit.Test
import kotlin.test.assertEquals

class DayNineTest {

    val input = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    val solver = DayNine(input)

    @Test
    fun testP1Ex1() {
        assertEquals(15, solver.partOne())
    }

    @Test
    fun testP2Ex1() {
        assertEquals(3, solver.getBasinSize(Point(0, 0)))
    }

    @Test
    fun testP2Ex2() {
        assertEquals(9, solver.getBasinSize(Point(6, 0)))
    }

    @Test
    fun testP2Ex3() {
        assertEquals(14, solver.getBasinSize(Point(2, 1)))
    }

    @Test
    fun testP2Answer() {
        assertEquals(1134, solver.partTwo())
    }
}
