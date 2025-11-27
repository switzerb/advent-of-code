package aoc.y2020

import aoc.lib.Resources.fileAsList
import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day19Test {

    private val input1 = """
        0: 1 2
        1: "a"
        2: 1 3 | 3 1
        3: "b"
         
        aab
        aba
        aaa
        bbb
    """.trimIndent()

    private val input2 = """
        0: 4 1 5
        1: 2 3 | 3 2
        2: 4 4 | 5 5
        3: 4 5 | 5 4
        4: "a"
        5: "b"
        
        ababbb
        bababa
        abbbab
        aaabbb
        aaaabbb
    """.trimIndent()

    @Test
    fun testRuleParse() {
        val solver = Day19(rawAsList(input1))
        assertTrue(solver.ruleMatch("aab"))
        assertTrue(solver.ruleMatch("aba"))
        assertFalse(solver.ruleMatch("aaa"))
        assertFalse(solver.ruleMatch("bbb"))
    }

    @Test
    fun testRuleCount() {
        val solver = Day19(rawAsList(input1))
        assertEquals(2,solver.partOne())
    }

    @Test
    fun testRuleParse2() {
        val solver = Day19(rawAsList(input2))
        assertTrue(solver.ruleMatch("ababbb"))
    }

    @Test
    fun testRuleParse3() {
        val solver = Day19(rawAsList(input2))
        assertFalse(solver.ruleMatch("aaaabbb"))
    }

    @Test
    fun testExample2() {
        val solver = Day19(rawAsList(input2))
        assertEquals(2, solver.partOne())
    }

    @Test
    fun testPartTwoNoLoop() {
        val solver = Day19(fileAsList("day19_2020_test.txt"))
        assertEquals(3, solver.partOne())
    }

    @Test
    fun testPartTwoMatcher1() {
        val solver = Day19(fileAsList("day19_2020_test.txt"))
        assertTrue(solver.matchPartTwo("bbabbbbaabaabba"))
    }

    @Test
    fun testPartTwoMatcher2() {
        val solver = Day19(fileAsList("day19_2020_test.txt"))
        assertTrue(solver.matchPartTwo("babbbbaabbbbbabbbbbbaabaaabaaa"))
    }

    @Test
    fun testPartTwoLoop() {
        val solver = Day19(fileAsList("day19_2020_test.txt"))
        assertEquals(12, solver.partTwo())
    }

}
