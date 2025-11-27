package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day162020Test  {

    val input = """
        class: 1-3 or 5-7
        row: 6-11 or 33-44
        seat: 13-40 or 45-50

        your ticket:
        7,1,14

        nearby tickets:
        7,3,47
        40,4,50
        55,2,20
        38,6,12
    """.trimIndent()


    val input2 = """
        class: 0-1 or 4-19
        row: 0-5 or 8-19
        seat: 0-13 or 16-19

        your ticket:
        11,12,13

        nearby tickets:
        3,9,18
        15,1,5
        5,14,9
    """.trimIndent()

    @Test
    fun ex1() {
        val solver = Day162020(rawAsList(input))
        assertEquals(71, solver.partOne())
    }

    @Test
    fun testValid() {
        val solver = Day162020(rawAsList(input))
        assertEquals(listOf(Ticket(listOf(7,3,47))), solver.getValidTickets())
    }

    @Test
    fun ex2() {
        val solver = Day162020(rawAsList(input2))
        assertEquals(0, solver.partTwo())
    }
}
