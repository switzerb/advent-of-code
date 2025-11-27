package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyOneTest {

    val input = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
    """.trimIndent()

    val solver = DayTwentyOne(input)

    @Test
    fun t1_p1() {
        assertEquals(152, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(301, solver.partTwo())
    }

    @Test
    fun t2_p2() {
        val input = """
         root: humn + sjmn
         sjmn: 3
         humn: 5
        """.trimIndent()
        val solver = DayTwentyOne(input)
        assertEquals(3, solver.partTwo())
    }
}
