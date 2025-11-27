package aoc.y2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TeamMonkeyTest {

    @Test
    fun solveForLeft() {
        assertEquals(1, TeamMonkey(op = "+").solveForLeft(3, 2))
        assertEquals(5, TeamMonkey(op = "-").solveForLeft(3, 2))
        assertEquals(2, TeamMonkey(op = "*").solveForLeft(6, 3))
        assertEquals(18, TeamMonkey(op = "/").solveForLeft(6, 3))
    }

    @Test
    fun solveForRight() {
        assertEquals(1, TeamMonkey(op = "+").solveForRight(3, 1))
    }
}
