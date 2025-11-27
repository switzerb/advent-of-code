package aoc.y2020

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class DayFive2020Test {

    @Test fun testPartOne() {
        val input = """
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent()
        val solver = DayFive2020(input)
        assertEquals(820, solver.partOne())
    }

    @Test fun ex1() {
        val solver = DayFive2020("FBFBBFFRLR")
        assertEquals(357,solver.partOne())
    }

    @Test fun ex2() {
        val solver = DayFive2020("BFFFBBFRRR")
        assertEquals(567, solver.partOne())
    }

    @Test fun ex3() {
        val solver = DayFive2020("FFFBBBFRRR")
        assertEquals(119, solver.partOne())
    }

    @Test fun ex4() {
        val solver = DayFive2020("BBFFBBFRLL")
        assertEquals(820, solver.partOne())
    }

}
