package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayThirteenTest {

    val input = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0
    """.trimIndent()

    val instructions = """
        fold along y=7
        fold along x=5
    """.trimIndent()

    @Test
    fun test() {
        val solver = DayThirteen(input)
        assertEquals(17, solver.partOne())
    }
}
