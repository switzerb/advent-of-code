package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals

class DayEleven2020Test {

    val input = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent()

    val solver = DayEleven2020(input)

    @Test
    fun ex1() {
        assertEquals(37, solver.partOne())
    }

    @Test
    fun ex2() {
        assertEquals(26, solver.partTwo())
    }

    @Test
    fun testToCoordinates() {
        val seatMap = solver.getInitial()
        assertEquals(Point(0,0), seatMap.toPoint(0))
        assertEquals(Point(1,0), seatMap.toPoint(10))
        assertEquals(Point(1,3), seatMap.toPoint(13))
    }

    @Test
    fun testToIndex() {
        val seatMap = solver.getInitial()
        assertEquals(0, seatMap.fromPoint(Point(0,0)))
        assertEquals(10, seatMap.fromPoint(Point(1,0)))
        assertEquals(13, seatMap.fromPoint(Point(1,3)))
    }

    @Test
    fun testTickFirst() {
        val second = """
            #.##.##.##
            #######.##
            #.#.#..#..
            ####.##.##
            #.##.##.##
            #.#####.##
            ..#.#.....
            ##########
            #.######.#
            #.#####.##
        """.trimIndent()

        val seatMap = solver.getInitial()
        val next = seatMap.tick()
        assertEquals(second, next.toString())
    }

    @Test
    fun testTickSecond() {
        val expected = """
            #.LL.L#.##
            #LLLLLL.L#
            L.L.L..L..
            #LLL.LL.L#
            #.LL.LL.LL
            #.LLLL#.##
            ..L.L.....
            #LLLLLLLL#
            #.LLLLLL.L
            #.#LLLL.##
        """.trimIndent()
        val seatMap = solver.getInitial()
        val next = seatMap.tick().tick()
        assertEquals(expected, next.toString())
    }

    @Test
    fun exSeatFind() {
        val input = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent()
        val grid = SeatGrid.parse(input)
        assertEquals(8,grid.getLineOfSightNeighbors(39).size)
    }

    @Test
    fun exSeatFind2() {
        val input = """
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent()
        val grid = SeatGrid.parse(input)
        assertEquals(0,grid.getLineOfSightNeighbors(24).size)
    }

}
