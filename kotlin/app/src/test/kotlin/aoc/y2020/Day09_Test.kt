package aoc.y2020

import aoc.lib.Resources.rawAsLongs
import org.junit.Test
import kotlin.test.assertEquals

class DayNine2020Test {

    val input = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent()

    @Test
    fun part1() {
        val solver = DayNine2020(rawAsLongs(input), 5)
        assertEquals(127, solver.partOne())
    }

    @Test
    fun part2() {
        val solver = DayNine2020(rawAsLongs(input), 5)
        assertEquals(62, solver.partTwo())
    }
}
