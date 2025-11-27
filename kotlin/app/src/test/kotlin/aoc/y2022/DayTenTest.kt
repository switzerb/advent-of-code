package aoc.y2022

import aoc.lib.Resources.fileAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayTenTest {

    val solver = DayTen(fileAsList("2022/day10_2022.txt"))

    @Test
    fun test_p1e1() = assertEquals(13140, solver.partOne())

    @Test
    fun test_p2e1() {
        val expected = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
            
        """.trimIndent()
        assertEquals(expected, solver.partTwo())
    }
}
