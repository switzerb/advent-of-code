package aoc.y2022

import aoc.lib.Resources.fileAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayThreeTest {

    val input = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    @Test
    fun testp1e1() {
        val solver = DayThree(input)
        assertEquals(157, solver.partOne())
    }

    @Test
    fun testp2e1() {
        val solver = DayThree(input)
        assertEquals(70, solver.partTwo())
    }

    @Test
    fun testp1_final() {
        val solver = DayThree(fileAsList("2022/day03_2022.txt"))
        assertEquals(7811, solver.partOne())
    }

    @Test
    fun testp2_final() {
        val solver = DayThree(fileAsList("2022/day03_2022.txt"))
        assertEquals(2639, solver.partTwo())
    }
}
