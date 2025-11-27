package aoc.y2022

import aoc.lib.Resources
import org.junit.Test
import kotlin.test.assertEquals

class DaySevenTest {

    val input = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k"
    )

    val actual = DaySeven(Resources.fileAsList("2022/day07_2022.txt"))

    @Test
    fun test_p1e1() {
        val solver = DaySeven(input)
        assertEquals(95437, solver.partOne())
    }

    @Test
    fun test_p2e1() {
        val solver = DaySeven(input)
        assertEquals(95437, solver.partOne())
    }

    @Test
    fun test_p1_solution() {
        assertEquals(1444896, actual.partOne())
    }

    @Test
    fun test_p2_solution() {
        assertEquals(404395, actual.partTwo())
    }
}
