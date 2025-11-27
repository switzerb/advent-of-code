package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals

class DayThirteen2020Test {

    private val depart = 939
    val input = listOf("7","13","x","x","59","x","31","19")

    val solver = DayThirteen2020(depart,input)

    @Test
    fun ex1() {
        assertEquals(295, solver.partOne())
    }

    @Test
    fun ex2() {
        assertEquals(1068781, solver.partTwo())
    }
}
