package aoc.y2022

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DayThirteenTest {

    val input = """
        [1,1,3,1,1]
        [1,1,5,1,1]

        [[1],[2,3,4]]
        [[1],4]

        [9]
        [[8,7,6]]

        [[4,4],4,4]
        [[4,4],4,4,4]

        [7,7,7,7]
        [7,7,7]

        []
        [3]

        [[[]]]
        [[]]

        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]
    """.trimIndent()

    val solver = DayThirteen(rawAsList(input))

    @Test
    fun t1_p1() {
        assertEquals(13, solver.partOne())
    }

    @Test
    fun t1_p2() {
        assertEquals(140, solver.partTwo())
    }

    @Test
    fun t4_p1() {
        assertTrue(solver.inOrder(Packet.fromString("[100,12,13]"), Packet.fromString("[100,20,9]")))
    }

    @Test
    fun t5_p1() {
        assertEquals(-1, Packet.fromString("[1]").compareTo(Packet.fromString("[2]")))
        assertEquals(-1, Packet.fromString("[1]").compareTo(Packet.fromString("[[[2]]]")))
        assertEquals(-1, Packet.fromString("[[[1]]]").compareTo(Packet.fromString("[2]")))
        assertEquals(-1, Packet.fromString("[1]").compareTo(Packet.fromString("[1,2]")))
        assertEquals(-1, Packet.fromString("[1,2]").compareTo(Packet.fromString("[2]")))
        assertEquals(1, Packet.fromString("[2,1]").compareTo(Packet.fromString("[2]")))
        assertEquals(1, Packet.fromString("[2]").compareTo(Packet.fromString("[1]")))
        assertEquals(1, Packet.fromString("[2,1]").compareTo(Packet.fromString("[1,2]")))
        assertEquals(0, Packet.fromString("[1]").compareTo(Packet.fromString("[1]")))
    }

    @Test
    fun t2_p1() {
//        assertTrue(
//            solver.inOrder(
//                Packet.fromString("[1,1,3,1,1]"),
//                Packet.fromString("[1,1,15,1,1]")
//            )
//        )
//        assertTrue(
//            solver.inOrder(
//                Packet.fromString("[[1],[2,3,4]]"),
//                Packet.fromString("[[1],4]")
//            )
//        )
//        assertTrue(
//            solver.inOrder(
//                Packet.fromString("[[4,4],4,4]"),
//                Packet.fromString("[[4,4],4,4,4]")
//            )
//        )
//        assertTrue(
//            solver.inOrder(
//                Packet.fromString("[]"),
//                Packet.fromString("[3]")
//            )
//        )
//        assertFalse(
//            solver.inOrder(
//                Packet.fromString("[9]"),
//                Packet.fromString("[[8,7,6]]")
//            )
//        )
//        assertFalse(
//            solver.inOrder(
//                Packet.fromString("[7,7,7,7]"),
//                Packet.fromString("[7,7,7]")
//            )
//        )
//        assertFalse(
//            solver.inOrder(
//                Packet.fromString("[1,[2,[3,[4,[5,6,7]]]],8,9]"),
//                Packet.fromString("[1,[2,[3,[4,[5,6,0]]]],8,9]")
//            )
//        )
//        assertFalse(
//            solver.inOrder(
//                Packet.fromString("[[[]]]"),
//                Packet.fromString("[[]]")
//            )
//        )
        assertTrue(
            solver.inOrder(
                Packet.fromString("[[4]]"),
                Packet.fromString("[[[4,[4,0,5,7],2],[],[9,[1]],2,10],[[0,8,7],8,7,10],[],[3]]")
            )
        )
        assertFalse(
            solver.inOrder(
                Packet.fromString("[[7],[],[5,[[0],[],[4,5,2,3,0],[6,4],[5,9,2,4,1]]],[]]"),
                Packet.fromString("[[],[9,[8,[5,7,7,3],3,[7,0,3,10,0],7]]]")
            )
        )
    }
}
