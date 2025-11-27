package aoc.y2020

import junit.framework.Assert.assertEquals
import org.junit.Test

class DayOne2020Test {
    private val input = """
            1721
            979
            366
            299
            675
            1456
    """.trimIndent()

    @Test
    fun testEx1() {
        val solver = DayOne2020(input)
        assertEquals(514579, solver.partOne())
    }

    @Test
    fun textEx2() {
        val solver = DayOne2020(input)
        assertEquals(241861950, solver.partTwo())
    }
}
