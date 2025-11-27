package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day21Test {

    val example = """
        mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
        trh fvjkl sbzzf mxmxvkd (contains dairy)
        sqjhc fvjkl (contains soy)
        sqjhc mxmxvkd sbzzf (contains fish)
    """.trimIndent()

    val input = rawAsList(example)
    val solver = Day21(input)

    @Test
    fun testNonAllergenic() {
        assertEquals(setOf("kfcds", "nhms", "sbzzf", "trh"), solver.nonAllergenic())
    }

    @Test
    fun partOne() {
        assertEquals(5, solver.partOne())
    }

    @Test
    fun testBuildPotentials() {
        assertEquals("{dairy=[mxmxvkd], fish=[mxmxvkd, sqjhc], soy=[sqjhc, fvjkl]}", solver.buildPotentials().toString())
    }

    @Test
    fun partTwo() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", solver.partTwo())
    }
}
