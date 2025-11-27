package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class DaySeven2020Test {

    val input = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent()

    val input2 = """
        shiny gold bags contain 2 dark red bags.
        dark red bags contain 2 dark orange bags.
        dark orange bags contain 2 dark yellow bags.
        dark yellow bags contain 2 dark green bags.
        dark green bags contain 2 dark blue bags.
        dark blue bags contain 2 dark violet bags.
        dark violet bags contain no other bags.
    """.trimIndent()

    @Test
    fun ex1() {
        val solver = DaySeven2020(rawAsList(input))
        assertEquals(4, solver.partOne())
    }

    @Test
    fun ex1p2() {
        val solver = DaySeven2020(rawAsList(input))
        assertEquals(32, solver.partTwo())
    }

    @Test
    fun ex2p2() {
        val solver = DaySeven2020(rawAsList(input2))
        assertEquals(126, solver.partTwo())
    }
}
