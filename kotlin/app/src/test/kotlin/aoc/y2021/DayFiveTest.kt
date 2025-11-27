package aoc.y2021

import aoc.lib.Resources.rawAsList
import aoc.lib.Vector
import org.junit.Assert.assertEquals
import org.junit.Test

class DayFiveTest {

    val input = """
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
    """.trimIndent()

    val solver = DayFive(rawAsList(input))

    @Test
    fun test() {
        assertEquals(solver.partOne(), 5)
    }

    @Test
    fun expandLinesTest() {
        val expandedY = listOf(
            Vector(1, 1),
            Vector(1, 2),
            Vector(1, 3),
        )
        val expandedX = listOf(
            Vector(7, 7),
            Vector(8, 7),
            Vector(9, 7),
        )
        val expandedDiag1 = listOf(
            Vector(1, 1),
            Vector(2, 2),
            Vector(3, 3)
        )
        val expandedDiag2 = listOf(
            Vector(9, 7),
            Vector(8, 8),
            Vector(7, 9),
        )
        assertEquals(expandedY, solver.expandLines(Vector(1, 1), Vector(1, 3)))
        assertEquals(expandedX, solver.expandLines(Vector(9, 7), Vector(7, 7)))
        assertEquals(expandedDiag1, solver.expandLines(Vector(1, 1), Vector(3, 3)))
        assertEquals(expandedDiag2, solver.expandLines(Vector(9, 7), Vector(7, 9)))
    }
}
