package aoc.y2020

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DayTwoTest {

    val input = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent()

    @Test fun testSledPasswordValid() {
        val password = Password(1,3,'a', "abcde")
        assertTrue(password.isSledValid())
    }

    @Test fun testSledPasswordInvalid() {
        val password = Password(1,3, 'b', "cdefg")
        assertFalse(password.isSledValid())
    }

    @Test fun testTobogganPasswordValid() {
        val password = Password(1,3,'a', "abcde")
        assertTrue(password.isTobogganValid())
    }

    @Test fun testTobogganPasswordInvalidBoth() {
        val password = Password(2,9, 'c', "ccccccccc")
        assertFalse(password.isTobogganValid())
    }

    @Test fun testTobogganPasswordInvalidNeither() {
        val password = Password(1,3, 'b', "cdefg")
        assertFalse(password.isTobogganValid())
    }

    @Test fun testExample1() {
        val solver = DayTwo2020(input)
        assertEquals(2, solver.partOne())
    }

    @Test fun testExample2() {
        val solver = DayTwo2020(input)
        assertEquals(1, solver.partTwo())
    }

}
