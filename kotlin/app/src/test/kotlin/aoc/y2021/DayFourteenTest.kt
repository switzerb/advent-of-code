package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayFourteenTest {

    val input = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    val solver = DayFourteen(input)

    @Test
    fun test() {
        val solver = DayFourteen(input)
        assertEquals(1588, solver.partOne())
    }

    @Test
    fun testP2Ex1() {
        assertEquals(2188189693529, solver.partTwo())
    }

}
