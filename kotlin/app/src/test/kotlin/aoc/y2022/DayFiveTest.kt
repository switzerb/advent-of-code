package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayFiveTest {

    val input = listOf(
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )

    @Test
    fun testp1e1() {
        val solver = DayFive(input)
        assertEquals("CMZ", solver.partOne())
    }

    @Test
    fun testp2e1() {
        val solver = DayFive(input)
        assertEquals("MCD", solver.partTwo())
    }
}
