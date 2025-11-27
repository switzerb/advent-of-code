package aoc.y2021

import aoc.lib.Resources.rawAsList
import org.junit.Assert.assertEquals
import org.junit.Test

class DayOneTest {

    val input = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
    """.trimIndent()

    val solver = DayOne(rawAsList(input))

    @Test
    fun testEx1() {
        assertEquals(7, solver.partOne())
    }

    @Test
    fun testEx1P2() {
        assertEquals(5, solver.partTwo())
    }

}
