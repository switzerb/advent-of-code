package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayFifteenTest {

    val input = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent()

    val simpleGrid = """
        123
        456
    """.trimIndent()

    val simplestGrid = """
        11
        11
    """.trimIndent()

    @Test
    fun testP1Ex1() {
        val solver = DayFifteen(simplestGrid)
        assertEquals(2, solver.partOne())
    }

    @Test
    fun testP1Ex2() {
        val solver = DayFifteen(simpleGrid)
        assertEquals(11, solver.partOne())
    }

    @Test
    fun testP1Ex3() {
        val solver = DayFifteen(input)
        assertEquals(40, solver.partOne())
    }

    @Test
    fun textP2Ex1() {
        val solver = DayFifteen(input)
        assertEquals(315, solver.partTwo())
    }
}