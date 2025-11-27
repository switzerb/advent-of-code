package aoc.y2022

import aoc.lib.Resources.fileAsString
import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyTest {

    val input = """
            1
            2
            -3
            3
            -2
            0
            4
    """.trimIndent()
    val solver = DayTwenty(input)

    @Test
    fun t1_p1() {
        assertEquals(3, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(1623178306, solver.partTwo())
    }

    @Test
    fun actual_p2() {
        val input = fileAsString("2022/day21_2022.txt")
        val solver = DayTwenty(input)
        assertEquals(1595584274798, solver.partTwo())
    }
}
