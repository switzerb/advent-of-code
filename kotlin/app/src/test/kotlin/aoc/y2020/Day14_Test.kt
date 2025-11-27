package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day142020Test {

    val input = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
    """.trimIndent()

    val program = """
        mask = 000000000000000000000000000000X1001X
        mem[42] = 100
        mask = 00000000000000000000000000000000X0XX
        mem[26] = 1
    """.trimIndent()

    val solver = Day14y2020(rawAsList(input))

    @Test
    fun ex1() {
        assertEquals(165, solver.partOne())
    }

    @Test
    fun testToBinary() {
        assertEquals("000000000000000000000000000000101010", solver.toBinary("42"))
    }

    @Test
    fun testFloatAddress() {
        val addr = "000000000000000000000000000000101010".toCharArray()
        val mask = "000000000000000000000000000000X1001X"
        val expected = "000000000000000000000000000000X1101X"
        assertEquals(expected, solver.floatAddress(addr, mask))
    }

    @Test
    fun ex2() {
        val solver = Day14y2020(rawAsList(program))
        assertEquals(208, solver.partTwo())
    }
}
