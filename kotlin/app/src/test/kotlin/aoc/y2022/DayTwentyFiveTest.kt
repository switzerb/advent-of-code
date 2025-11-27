package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyFiveTest {

    val input = """
        1=-0-2
        12111
        2=0=
        21
        2=01
        111
        20012
        112
        1=-1=
        1-12
        12
        1=
        122
    """.trimIndent()

    val solver = DayTwentyFive(input)

    @Test
    fun toSnafuTest() {
        assertEquals("1", solver.toSnafu(1))
        assertEquals("2", solver.toSnafu(2))
        assertEquals("1=", solver.toSnafu(3))
        assertEquals("1-", solver.toSnafu(4))
        assertEquals("10", solver.toSnafu(5))
        assertEquals("11", solver.toSnafu(6))
        assertEquals("12", solver.toSnafu(7))
        assertEquals("2=", solver.toSnafu(8))
        assertEquals("2-", solver.toSnafu(9))
        assertEquals("20", solver.toSnafu(10))
        assertEquals("1=0", solver.toSnafu(15))
        assertEquals("1-0", solver.toSnafu(20))
        assertEquals("1=11-2", solver.toSnafu(2022))
        assertEquals("1-0---0", solver.toSnafu(12345))
        assertEquals("1121-1110-1=0", solver.toSnafu(314159265))
    }

    @Test
    fun fromSnafuTest() {
        assertEquals(3, solver.fromSnafu("1="))
        assertEquals(1747, solver.fromSnafu("1=-0-2"))
        assertEquals(906, solver.fromSnafu("12111"))
        assertEquals(198, solver.fromSnafu("2=0="))
        assertEquals(11, solver.fromSnafu("21"))
        assertEquals(201, solver.fromSnafu("2=01"))
        assertEquals(31, solver.fromSnafu("111"))
        assertEquals(1257, solver.fromSnafu("20012"))
        assertEquals(32, solver.fromSnafu("112"))
        assertEquals(353, solver.fromSnafu("1=-1="))
        assertEquals(107, solver.fromSnafu("1-12"))
        assertEquals(7, solver.fromSnafu("12"))
        assertEquals(37, solver.fromSnafu("122"))
    }

    @Test
    fun t1_p1() {
        assertEquals("2=-1=0", solver.partOne())
    }
}
