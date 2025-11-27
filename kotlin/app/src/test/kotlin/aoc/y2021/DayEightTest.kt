package aoc.y2021

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class DayEightTest {

    val input = """
        acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
        cdfeb fcadb cdfeb cdbaf
    """.trimIndent()

    val input2 = """
        be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
        edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
        fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
        fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
        aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
        fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
        dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
        bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
        egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
        gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
    """.trimIndent()

    val solver = DayEight(rawAsList(input2))

    @Test
    fun testP1() {
        assertEquals(26, solver.partOne())
    }

    @Test
    fun testP2() {
        val digits = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(' ')
        val output = "cdfeb fcadb cdfeb cdbaf"
            .split(' ')
            .map {
                it.toSortedSet().joinToString("")
            }

        assertEquals(5353, solver.getTranslated(digits, output))
    }

    @Test
    fun testP2Ex2() {
        assertEquals(61229, solver.partTwo())
    }

    @Test
    fun testP2Ex3() {
        val digits = "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega".split(' ')
        val output = "efabcd cedba gadfec cb"
            .split(' ')
            .map {
                it.toSortedSet().joinToString("")
            }
        assertEquals(9361, solver.getTranslated(digits, output))
    }

    @Test
    fun testP2Ex4() {
        val digits = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb"
            .split(' ')
            .map {
                it.toSortedSet().joinToString("")
            }
        val output = "fdgacbe cefdb cefbgd gcbe"
            .split(' ')
            .map {
                it.toSortedSet().joinToString("")
            }
        assertEquals(8394, solver.getTranslated(digits, output))
    }
}
