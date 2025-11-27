package aoc.y2022

import aoc.lib.Resources.fileAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayTwelveTest {

    val input = listOf(
        "Sabqponm",
        "abcryxxl",
        "accszExk",
        "acctuvwj",
        "abdefghi"
    )

    @Test
    fun test_p1e1() {
        val solver = DayTwelve(input)
        assertEquals(31, solver.partOne())
    }

    @Test
    fun test_p2e1() {
        val solver = DayTwelve(input)
        assertEquals(29, solver.partTwo())
    }

    @Test
    fun test_p1_actual() {
        val solver = DayTwelve(fileAsList("2022/day12_2022.txt"))
        assertEquals(447, solver.partOne())
    }

    @Test
    fun test_p2_actual() {
        val solver = DayTwelve(fileAsList("2022/day12_2022.txt"))
        assertEquals(446, solver.partTwo())
    }
}
