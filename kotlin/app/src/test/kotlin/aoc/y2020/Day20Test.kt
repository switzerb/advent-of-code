package aoc.y2020

import aoc.lib.Resources.fileAsString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day20Test {

    private val pixels = """
        ..##.#..#.
        ##..#.....
        #...##..#.
        ####.#...#
        ##.##.###.
        ##...#.###
        .#.#.#..##
        ..#....#..
        ###...#.#.
        ..###..###
    """.trimIndent()

    @Test
    fun testGetBorder() {
        val tile = Tile(2311,pixels)
        assertEquals("..##.#..#.", tile.getBorder(Orientation.Top))
        assertEquals("..###..###", tile.getBorder(Orientation.Bottom))
        assertEquals(".#####..#.", tile.getBorder(Orientation.Left))
        assertEquals("...#.##..#", tile.getBorder(Orientation.Right))
    }

    @Test
    fun testCountTotalBorders() {
        val input = fileAsString("day20_2020_test.txt")
        val solver = Day20(input)
        assertEquals(9 * 8, solver.countTotalEdges())
    }

    @Test
    fun testHasEdgeMatch() {
        val tile = Tile(2311,pixels)
        assertTrue(tile.hasMatch(".#####..#."))
    }

    @Test
    fun testSharedBorderCount() {
        val tile = Tile(2311, """
            ..##.#..#.
            ##..#.....
            #...##..#.
            ####.#...#
            ##.##.###.
            ##...#.###
            .#.#.#..##
            ..#....#..
            ###...#.#.
            ..###..###
        """.trimIndent())
        val other = Tile(1951, """
            #.##...##.
            #.####...#
            .....#..##
            #...######
            .##.#....#
            .###.#####
            ###.##.##.
            .###....#.
            ..#.#..#.#
            #...##.#..     
        """.trimIndent())
        assertTrue(tile.hasMatchedEdge(other))
    }

    @Test
    fun testParser() {
        val input = fileAsString("day20_2020_test.txt")
        val solver = Day20(input)
        assertEquals(9, solver.parse().size)
    }

    @Test
    fun testPartOneExample() {
        val input = fileAsString("day20_2020_test.txt")
        val solver = Day20(input)
        assertEquals(20899048083289, solver.partOne())
    }

    @Test
    fun testPartOne() {
        val input = fileAsString("day20_2020.txt")
        val solver = Day20(input)
        assertEquals(174206308298779, solver.partOne())
    }

    @Test
    fun testFlipTile() {
        val tile = Tile(2311, """
            #.
            .#
        """.trimIndent())
        val flipped = Tile(2311, """
            .#
            #.
        """.trimIndent())
        assertEquals(flipped.toString(),tile.flip().toString())
    }

    @Test
    fun testFlipTileHarder() {
        val tile = Tile(2311, """
            ..#
            .#.
            #..
        """.trimIndent())
        val flipped = Tile(2311, """
            #..
            .#.
            ..#
        """.trimIndent())
        assertEquals(flipped.toString(),tile.flip().toString())
    }

    @Test
    fun testRotate() {
        val tile = Tile(2311, """
            .#
            ..
        """.trimIndent())
        val rotated = Tile(2311, """
            ..
            .#
        """.trimIndent())
        assertEquals(rotated.toString(),tile.rotateClockwise().toString())
    }

    @Test
    fun testRotateTileHarder() {
        val tile = Tile(2311, """
            ###
            ...
            ...
        """.trimIndent())
        val rotated = Tile(2311, """
            ..#
            ..#
            ..#
        """.trimIndent())
        assertEquals(rotated.toString(),tile.rotateClockwise().toString())
    }

}
