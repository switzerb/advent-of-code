package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayElevenTest {

    val input = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent()

    val input2 = """
        11111
        19991
        19191
        19991
        11111
    """.trimIndent()

    @Test
    fun testStep() {
        val solver = DayEleven(input)
        assertEquals(1656, solver.partOne())
    }
}
