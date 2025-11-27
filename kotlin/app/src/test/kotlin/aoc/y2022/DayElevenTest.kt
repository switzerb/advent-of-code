package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayElevenTest {

    val input = listOf(
        Monkey(
            items = mutableListOf(79, 98),
            worryOperation = { w -> w * 19 },
            divider = 23,
            monkeyTId = 2,
            monkeyFId = 3
        ),
        Monkey(
            items = mutableListOf(54, 65, 75, 74),
            worryOperation = { w -> w + 6 },
            divider = 19,
            monkeyTId = 2,
            monkeyFId = 0
        ),
        Monkey(
            items = mutableListOf(79, 60, 97),
            divider = 13,
            worryOperation = { w -> w * w },
            monkeyTId = 1,
            monkeyFId = 3
        ),
        Monkey(
            items = mutableListOf(74),
            divider = 17,
            worryOperation = { w -> w + 3 },
            monkeyTId = 0,
            monkeyFId = 1
        )
    )
    val solver = DayEleven(input)

    @Test
    fun test_p1e1() {
        assertEquals(10605, solver.partOne())
    }

    @Test
    fun test_p2e1() {
        assertEquals(2713310158, solver.partTwo())
    }

    @Test
    fun t2_p2() {
        solver.partTwo(20)
        assertEquals(99, solver.monkeys[0].getInspectedCount())
        assertEquals(97, solver.monkeys[1].getInspectedCount())
        assertEquals(8, solver.monkeys[2].getInspectedCount())
        assertEquals(103, solver.monkeys[3].getInspectedCount())
    }

    @Test
    fun t3_p2() {
        solver.partTwo(1000)
        assertEquals(5204, solver.monkeys[0].getInspectedCount())
        assertEquals(4792, solver.monkeys[1].getInspectedCount())
        assertEquals(199, solver.monkeys[2].getInspectedCount())
        assertEquals(5192, solver.monkeys[3].getInspectedCount())
    }
}
