package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DaySix2020Test {

    private val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
    private val solver = DaySix2020(input)

    @Test
    fun partOne() { assertEquals(11, solver.partOne()) }

    @Test
    fun partTwo() { assertEquals(6, solver.partTwo())}
}
