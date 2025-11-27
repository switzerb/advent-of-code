package aoc.y2016

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day09Test {

    @Test
    fun test_p1_1() {
        val solver = Day09("ADVENT")
        assertEquals(6, solver.partOne())
    }

    @Test
    fun test_p1_2() {
        val solver = Day09("A(1x5)BC")
        assertEquals(7, solver.partOne())
    }

    @Test
    fun test_p1_3() {
        val solver = Day09("(3x3)XYZ")
        assertEquals(9, solver.partOne())
    }

    @Test
    fun test_p1_4() {
        val solver = Day09("A(2x2)BCD(2x2)EFG")
        assertEquals(11, solver.partOne())
    }

    @Test
    fun test_p1_5() {
        val solver = Day09("X(8x2)(3x3)ABCY")
        assertEquals(18, solver.partOne())
    }

    @Test
    fun test_p1_6() {
        val solver = Day09("(6x1)(1x3)A")
        assertEquals(6, solver.partOne())
    }
}
