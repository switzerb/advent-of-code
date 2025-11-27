package aoc.y2016

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DayOne2016Test {

    val input1 = "R2, L3"

    @Test
    fun ex1() {
        val solver = DayOne2016(input1)
        assertEquals(0, solver.partOne())
    }
}
