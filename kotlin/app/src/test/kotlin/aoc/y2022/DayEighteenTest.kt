package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayEighteenTest {

    val input = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5
    """.trimIndent()

    val solver = DayEighteen(input)

    @Test
    fun t1_p1() {
        assertEquals(64, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(58, solver.partTwo())
    }
}
