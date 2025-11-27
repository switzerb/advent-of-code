package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DayThreeTest {
    private val input = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent()

    @Test fun testPartOne() {
        val solver = DayThree2020(input)
        assertEquals(7, solver.partOne())
    }

    @Test fun testGrid() {
        val solver = DayThree2020(input)
        assertEquals(336, solver.partTwo())
    }

}
