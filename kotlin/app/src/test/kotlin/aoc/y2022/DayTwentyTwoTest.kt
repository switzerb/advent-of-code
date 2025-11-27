package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyTwoTest {

    val input = """
        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.

10R5L5R10L4R5L5
    """.trimIndent()
    val solver = DayTwentyTwo(input)

    @Test
    fun t1_p1() {
        assertEquals(6032, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(5031, solver.partTwo())
    }

    @Test
    fun t2_p1() {
        val player = Player(Position(8, 0), Direction.EAST)
        val expected = """
        >..#    
        .#..    
        #...    
        ....    
...#.......#    
........#...    
..#....#....    
..........#.    
        ...#....
        .....#..
        .#......
        ......#.
"""
        assertEquals(expected, solver.board.printBoard(player))
    }
}
