package aoc.y2021

import org.junit.Assert.assertEquals
import org.junit.Test

class DaySevenTest {

    val input = """
        16,1,2,0,4,2,7,1,2,14
    """.trimIndent()

    val solver = DaySeven(input)

    @Test
    fun testP1Ex1() {
        assertEquals(solver.costOfFuel(2), 37)
    }

    @Test
    fun testP1Ex2() {
        assertEquals(solver.costOfFuel(1), 41)
    }

    @Test
    fun testP1Ex3() {
        assertEquals(solver.costOfFuel(3), 39)
    }

    @Test
    fun testP1Ex4() {
        assertEquals(solver.costOfFuel(10), 71)
    }

    @Test
    fun testP1Ex5() {
        assertEquals(37, solver.partOne())
    }

    @Test
    fun testP2Ex1() {
        assertEquals(168, solver.costOfExponentialFuel(5))
    }

    @Test
    fun testP2Ex2() {
        assertEquals(206, solver.costOfExponentialFuel(2))
    }

    @Test
    fun testP2Ex3() {
        assertEquals(168, solver.partTwo())
    }

}
