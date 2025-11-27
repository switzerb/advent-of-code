package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DaySixTest {

    val input = """
        3,4,3,1,2
    """.trimIndent()

    @Test
    fun testPartOne() {
        val solver = DaySix(input)
        assertEquals(5934, solver.partOne())
    }

    @Test
    fun testPartTwo() {
        val solver = DaySix(input)
        assertEquals(26984457539, solver.partTwo())
    }
}
