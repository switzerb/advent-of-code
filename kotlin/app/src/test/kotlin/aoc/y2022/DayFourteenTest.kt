package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayFourteenTest {

    val input = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent()

    val solver = DayFourteen(input)

    @Test
    fun t1_p1() {
        assertEquals(24, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(93, solver.partTwo())
    }
}
