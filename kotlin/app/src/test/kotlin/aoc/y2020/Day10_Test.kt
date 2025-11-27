import aoc.lib.Resources.rawAsInts
import aoc.y2020.DayTen2020
import org.junit.Test
import kotlin.test.assertEquals

class DayTen2020Test {

    val input = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent()

    val input2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent()

    private val dayten_ex1 = DayTen2020(rawAsInts(input))
    val dayten_ex2 = DayTen2020(rawAsInts(input2))

    @Test
    fun ex1() {
        assertEquals(7*5,dayten_ex1.partOne())
    }

    @Test
    fun ex2() {
        assertEquals(22*10, dayten_ex2.partOne())
    }

//    0, 1, 4
//    1, 4, 5,
//    4, 5, 6
//    5, 6, 7
//    6, 7, 10,
//    7, 10, 11,
//    10, 11, 12,
//    15, 16, 19
//
//    exponents
//     0   1, 3, 1, 1, 1, 3,  1,  1,  3,  1,  3,  3
//    (0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
//    (0), 1, 4,    6, 7, 10, 11, 12, 15, 16, 19, (22)
//    (0), 1, 4, 5,    7, 10, 11, 12, 15, 16, 19, (22)
//    (0), 1, 4, 5, 6, 7, 10,     12, 15, 16, 19, (22)
//    (0), 1, 4, 5,    7, 10,     12, 15, 16, 19, (22)
//    (0), 1, 4,    6, 7, 10,     12, 15, 16, 19, (22)
//    (0), 1, 4,       7, 10, 11, 12, 15, 16, 19, (22)
//    (0), 1, 4,       7, 10,     12, 15, 16, 19, (22)
    @Test
    fun ex3() {
        assertEquals(8,dayten_ex1.partTwo())
    }

    @Test
    fun ex4() {
        assertEquals(19208, dayten_ex2.partTwo())
    }
}
