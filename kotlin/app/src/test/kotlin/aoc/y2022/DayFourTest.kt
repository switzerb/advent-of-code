package aoc.y2022

import org.junit.Assert.*
import org.junit.Test

class DayFourTest {

    val input = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )

    val parsedInput = listOf(
        listOf(Pair(2, 4), Pair(6, 9)),
        listOf(Pair(2, 3), Pair(4, 5)),
        listOf(Pair(5, 7), Pair(7, 9)),
        listOf(Pair(2, 8), Pair(3, 7)),
        listOf(Pair(6, 6), Pair(4, 6)),
        listOf(Pair(2, 6), Pair(4, 8))
    )

    @Test
    fun testp1e1() {
        val solver = DayFour(input)
        assertEquals(2, solver.partOne())
    }

    @Test
    fun testp1e2() {
        val solver = DayFour(listOf())
        assertTrue(solver.hasOverlap(listOf(Pair(5, 7), Pair(7, 9))))
        assertTrue(solver.hasOverlap(listOf(Pair(2, 2), Pair(2, 4))))
        assertTrue(solver.hasOverlap(listOf(Pair(3, 4), Pair(2, 3))))
        assertTrue(solver.hasOverlap(listOf(Pair(75, 76), Pair(18, 75))))
        assertTrue(solver.hasOverlap(listOf(Pair(2, 54), Pair(1, 50))))
        assertTrue(solver.hasOverlap(listOf(Pair(82, 83), Pair(78, 82))))
        assertTrue(solver.hasOverlap(listOf(Pair(13, 37), Pair(37, 75))))
        assertTrue(solver.hasOverlap(listOf(Pair(79, 80), Pair(2, 80))))
        assertTrue(solver.hasOverlap(listOf(Pair(29, 90), Pair(30, 89))))
        assertTrue(solver.hasOverlap(listOf(Pair(13, 16), Pair(12, 15))))
        assertTrue(solver.hasOverlap(listOf(Pair(20, 93), Pair(20, 94))))
        assertTrue(solver.hasOverlap(listOf(Pair(90, 95), Pair(33, 90))))
        assertTrue(solver.hasOverlap(listOf(Pair(16, 77), Pair(76, 92))))
        assertFalse(solver.hasOverlap(listOf(Pair(1, 2), Pair(3, 4))))
        assertFalse(solver.hasOverlap(listOf(Pair(97, 97), Pair(14, 84))))
    }

    @Test
    fun testp2e1() {
        val solver = DayFour(input)
        assertEquals(4, solver.partTwo())
    }
}
