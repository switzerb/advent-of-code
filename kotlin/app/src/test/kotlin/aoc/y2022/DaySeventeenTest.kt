package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DaySeventeenTest {

    val input = """
        >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
    """.trimIndent()

    val solver = DaySeventeen(input)

    @Test
    fun t1_p1() {
        assertEquals(3068, solver.partOne(rounds = 2022))
    }

    @Test
    fun t1_p12() {
        assertEquals(1514285714288, solver.partTwo())
    }
}
