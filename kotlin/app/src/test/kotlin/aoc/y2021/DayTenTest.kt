package aoc.y2021

import org.junit.Assert.assertEquals
import org.junit.Test

class DayTenTest {

    val chunk = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent()

    val solver = DayTen(chunk)

    @Test
    fun testP1Ex1() {
        assertEquals(26397, solver.partOne())
    }

    @Test
    fun testP2Ex1() {
        assertEquals(288957, solver.partTwo())
    }

    @Test
    fun testAutocompleteScore1() {
        val completion = "])}>".toCharArray().toList()
        assertEquals(294, solver.calculateAutocompleteScore(completion))
    }

    @Test
    fun testAutocompleteScore2() {
        val completion = "}}]])})]".toCharArray().toList()
        assertEquals(288957, solver.calculateAutocompleteScore(completion))
    }
}
