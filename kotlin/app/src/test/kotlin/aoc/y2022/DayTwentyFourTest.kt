package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyFourTest {

    val simple = """
        #.#####
        #.....#
        #>....#
        #.....#
        #...v.#
        #.....#
        #####.#
    """.trimIndent()

    val extended = """
        #.######
        #>>.<^<#
        #.<..<<#
        #>v.><>#
        #<^v^^>#
        ######.#
    """.trimIndent()

    @Test
    fun t1_p1() {
        val solver = DayTwentyFour(extended)
        assertEquals(18, solver.partOne())
    }

    @Test
    fun t1_p2() {
        val solver = DayTwentyFour(extended)
        assertEquals(54, solver.partTwo())
    }

    @Test
    fun t2_p1() {
        val solver = DayTwentyFour(simple)
        val expected = """
            #E#####
            #.....#
            #>....#
            #.....#
            #...v.#
            #.....#
            #####.#
            
        """.trimIndent()
        assertEquals(expected, solver.initialValley().toString())
    }

    @Test
    fun t3_p1() {
        val solver = DayTwentyFour(simple)
        val one = """
            #E#####
            #.....#
            #.>...#
            #.....#
            #.....#
            #...v.#
            #####.#

        """.trimIndent()
        val two = """
            #E#####
            #...v.#
            #..>..#
            #.....#
            #.....#
            #.....#
            #####.#

        """.trimIndent()

        val three = """
            #E#####
            #.....#
            #...2.#
            #.....#
            #.....#
            #.....#
            #####.#
            
        """.trimIndent()

        val four = """
            #E#####
            #.....#
            #....>#
            #...v.#
            #.....#
            #.....#
            #####.#
            
        """.trimIndent()

        val five = """
            #E#####
            #.....#
            #>....#
            #.....#
            #...v.#
            #.....#
            #####.#
            
        """.trimIndent()

        val min1 = solver.tick(current = solver.initialValley())
        val min2 = solver.tick(current = min1)
        val min3 = solver.tick(current = min2)
        val min4 = solver.tick(current = min3)
        val min5 = solver.tick(current = min4)
        assertEquals(one, min1.toString())
        assertEquals(two, min2.toString())
        assertEquals(three, min3.toString())
        assertEquals(four, min4.toString())
        assertEquals(five, min5.toString())
    }

    @Test
    fun t4_p1() {
        val solver = DayTwentyFour(extended)
        val init = """
            #E######
            #>>.<^<#
            #.<..<<#
            #>v.><>#
            #<^v^^>#
            ######.#
            
        """.trimIndent()

        val one = """
            #E######
            #.>3.<.#
            #<..<<.#
            #>2.22.#
            #>v..^<#
            ######.#
            
        """.trimIndent()

        val two = """
            #E######
            #.2>2..#
            #.^22^<#
            #.>2.^>#
            #.>..<.#
            ######.#
            
        """.trimIndent()

        val three = """
            #E######
            #<^<22.#
            #.2<.2.#
            #><2>..#
            #..><..#
            ######.#
            
        """.trimIndent()
        val start = solver.initialValley()
        val min1 = solver.tick(start)
        val min2 = solver.tick(min1)
        val min3 = solver.tick(min2)
        assertEquals(init, start.toString())
        assertEquals(one, min1.toString())
        assertEquals(two, min2.toString())
        assertEquals(three, min3.toString())
    }
}
