package aoc.y2022

import org.junit.Test
import kotlin.test.*

class DayEightTest {

    val input = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )

    val solver = DayEight(input)

    @Test
    fun test_p1e1() = assertEquals(21, solver.partOne())

    @Test
    fun test_p1e2() {
        val start = Point(1, 1)
        assertTrue(solver.isVisible(start, solver.lineOfSight(start, Dir.LEFT)))
        assertTrue(solver.isVisible(start, solver.lineOfSight(start, Dir.TOP)))
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.DOWN)))
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.RIGHT)))
    }

    @Test
    fun test_p1e3() {
        val start = Point(1, 3)
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.LEFT)))
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.TOP)))
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.DOWN)))
        assertFalse(solver.isVisible(start, solver.lineOfSight(start, Dir.RIGHT)))
    }

    @Test
    fun test_p2e1() {
        val start = Point(2, 1)
        assertEquals(1, solver.getScenicView(start, solver.lineOfSight(start, Dir.LEFT)))
        assertEquals(1, solver.getScenicView(start, solver.lineOfSight(start, Dir.TOP)))
        assertEquals(2, solver.getScenicView(start, solver.lineOfSight(start, Dir.DOWN)))
        assertEquals(2, solver.getScenicView(start, solver.lineOfSight(start, Dir.RIGHT)))
    }

    @Test
    fun test_p2e2() {
        val start = Point(2, 3)
        assertEquals(2, solver.getScenicView(start, solver.lineOfSight(start, Dir.LEFT)))
        assertEquals(2, solver.getScenicView(start, solver.lineOfSight(start, Dir.TOP)))
        assertEquals(1, solver.getScenicView(start, solver.lineOfSight(start, Dir.DOWN)))
        assertEquals(2, solver.getScenicView(start, solver.lineOfSight(start, Dir.RIGHT)))
    }

    @Test
    fun test_p2e3() {
        assertEquals(8, solver.partTwo())
    }
}
