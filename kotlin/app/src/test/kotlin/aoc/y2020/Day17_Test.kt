package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day17Test {

    val init = """
        .#.
        ..#
        ###
    """.trimIndent()

    @Test
    fun cycle() {
        val input = rawAsList(init)
        val pd = PocketDimension.parse(input)
        println(pd.cycle())
    }

    @Test
    fun testParse() {
        val input = rawAsList(init)
        val pd = PocketDimension.parse(input)
        println(pd)
    }

    @Test
    fun ex1() {
        val input = rawAsList(init)
        val solver = Day172020(input)
        assertEquals(112, solver.partOne())
    }

    @Test
    fun ex2() {
        val input = rawAsList(init)
        val solver = Day172020(input)
        assertEquals(848, solver.partTwo())
    }
}
