package aoc.y2020

import org.junit.Test
import kotlin.test.assertEquals

class Day152020Test {

    @Test
    fun ex1() {
        val solver = Day152020(listOf(0,3,6))
        assertEquals(436, solver.partOne())
    }

    @Test
    fun ex2() {
        val solver = Day152020(listOf(1,3,2))
        assertEquals(1, solver.partOne())
    }

    @Test
    fun ex3() {
        val solver = Day152020(listOf(2,1,3))
        assertEquals(10, solver.partOne())
    }

    @Test
    fun ex4() {
        val solver = Day152020(listOf(1,2,3))
        assertEquals(27, solver.partOne())
    }

    @Test
    fun ex5() {
        val solver = Day152020(listOf(2,3,1))
        assertEquals(78, solver.partOne())
    }

    @Test
    fun ex6() {
        val solver = Day152020(listOf(3,2,1))
        assertEquals(438, solver.partOne())
    }

    @Test
    fun ex7() {
        val solver = Day152020(listOf(3,1,2))
        assertEquals(1836, solver.partOne())
    }

}
