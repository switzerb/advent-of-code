package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun testOrder() {
        val solver = Day18(rawAsList("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(71, solver.partOne())
    }

    @Test
    fun testWithParens() {
        val solver = Day18(rawAsList("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(51, solver.partOne())
    }

    @Test
    fun testMore3() {
        val solver = Day18(rawAsList("2 * 3 + (4 * 5)"))
        assertEquals(26, solver.partOne())
    }

    @Test
    fun testMore4() {
        val solver = Day18(rawAsList("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        assertEquals(437, solver.partOne())
    }

    @Test
    fun testMore5() {
        val solver = Day18(rawAsList("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        assertEquals(12240, solver.partOne())
    }

    @Test
    fun testMore6() {
        val solver = Day18(rawAsList("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
        assertEquals(13632, solver.partOne())
    }

    @Test
    fun testWithPrecedence() {
        val solver = Day18(rawAsList("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(231, solver.partTwo())
    }

    @Test
    fun testWithPMore1() {
        val solver = Day18(rawAsList("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(51, solver.partTwo())
    }

    @Test
    fun testWithPMore2() {
        val solver = Day18(rawAsList("2 * 3 + (4 * 5)"))
        assertEquals(46, solver.partTwo())
    }

    @Test
    fun testWithPMore3() {
        val solver = Day18(rawAsList("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        assertEquals(1445, solver.partTwo())
    }

    @Test
    fun testWithPMore4() {
        val solver = Day18(rawAsList("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        assertEquals(669060, solver.partTwo())
    }

    @Test
    fun testWithPMore5() {
        val solver = Day18(rawAsList("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
        assertEquals(23340, solver.partTwo())
    }

}
