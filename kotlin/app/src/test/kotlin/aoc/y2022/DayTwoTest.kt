package aoc.y2022

import aoc.lib.Resources.fileAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayTwoTest {

    val input = fileAsList("2022/day02_2022.txt")

    @Test
    fun test_p1e1() {
        val solver = DayTwo(listOf("A Y"))
        assertEquals(8, solver.partOne())
    }

    @Test
    fun test_p1e2() {
        val solver = DayTwo(listOf("B X"))
        assertEquals(1, solver.partOne())
    }

    @Test
    fun test_p1e3() {
        val solver = DayTwo(listOf("C Z"))
        assertEquals(6, solver.partOne())
    }

    @Test
    fun test_p1e4() {
        val solver = DayTwo(input)
        assertEquals(15, solver.partOne())
    }

    @Test
    fun test_p2e1() {
        val solver = DayTwo(listOf("A Y"))
        assertEquals(4, solver.partTwo())
    }

    @Test
    fun test_p2e2() {
        val solver = DayTwo(listOf("B X"))
        assertEquals(1, solver.partTwo())
    }

    @Test
    fun test_p2e3() {
        val solver = DayTwo(listOf("A X"))
        assertEquals(3, solver.partTwo())
    }

    @Test
    fun test_p2e4() {
        val solver = DayTwo(listOf("A Z"))
        assertEquals(8, solver.partTwo())
    }

    @Test
    fun test_p2e30() {
        val solver = DayTwo(listOf("C Z"))
        assertEquals(7, solver.partTwo())
    }

    @Test
    fun test_p1_final() {
        val solver = DayTwo(input)
        assertEquals(8392, solver.partOne())
    }

    @Test
    fun test_p2_final() {
        val solver = DayTwo(input)
        assertEquals(10116, solver.partTwo())
    }
}
