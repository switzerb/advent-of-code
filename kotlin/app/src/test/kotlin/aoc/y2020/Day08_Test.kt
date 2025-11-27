package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals

class DayEight2020Test {

    val input = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent()

    @Test
    fun ex1() {
        val solver = DayEight2020(input)
        assertEquals(5, solver.partOne())
    }
}
