package aoc.y2022

import aoc.lib.Position
import aoc.lib.Resources.fileAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayNineTest {

    val input = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2"
    )

    val input2 = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20"
    )

    val solver = DayNine(input)

    @Test
    fun test_p1e1() {
        // MOVES WEST
        assertEquals(
            Position(2, 1),
            solver.calculateNext(
                head = Position(3, 1),
                next = Position(1, 1)
            )
        )
        // MOVES SOUTH
        assertEquals(
            Position(1, 2),
            solver.calculateNext(
                head = Position(1, 3),
                next = Position(1, 1)
            )
        )
        // MOVES EAST
        assertEquals(
            Position(2, 1),
            solver.calculateNext(
                head = Position(1, 1),
                next = Position(3, 1)
            )
        )
        // MOVES NORTH
        assertEquals(
            Position(1, 2),
            solver.calculateNext(
                head = Position(1, 1),
                next = Position(1, 3)
            )
        )
        // MOVES NORTHWEST
        assertEquals(
            Position(2, 2),
            solver.calculateNext(
                head = Position(2, 1),
                next = Position(1, 3)
            )
        )
    }

    @Test
    fun test_p1e10() = assertEquals(13, solver.partOne())

    @Test
    fun test_p2e1() = assertEquals(1, solver.partTwo())

    @Test
    fun test_p2e2() {
        val solver2 = DayNine(input2)
        assertEquals(36, solver2.partTwo())
    }

    @Test
    fun test_p1_actual() {
        val solution = DayNine(fileAsList("2022/day09_2022.txt"))
        assertEquals(6209, solution.partOne())
    }
}
