package aoc.y2020

import aoc.lib.Resources.fileAsList

class DaySeven2020(val input: List<String>) {

    data class Rule(val parent: String, val contains: Int, val child: String)

    private fun parseInput(input: List<String>): Set<Rule> =
        input.filterNot {
            it.contains("no other")
        }
            .flatMap { each ->
                val parts = each.replace("""bags|bag|contain|,|\.""".toRegex(), "").split("""\s+""".toRegex())
                val parent = parts.take(2).joinToString(" ")
                parts
                    .drop(2)
                    .windowed(3, 3, false)
                    .map { child ->
                        Rule(
                            parent,
                            child.first().toInt(),
                            child.drop(1).joinToString(" ")
                        )
                    }
            }.toSet()

    private val rules: Set<Rule> = parseInput(input)

    fun findOuterBag(bag: String = "shiny gold"): Set<String> {
        return rules
            .filter {
                it.child == bag
            }
            .flatMap {
                findOuterBag(it.parent)
            }
            .toSet() + bag
    }

    fun findTotals(bag: String = "shiny gold"): Int =
        rules
            .filter { it.parent == bag }
            .sumBy { it.contains * findTotals(it.child) } + 1

    fun partOne(): Int {
        return findOuterBag().size - 1
    }

    fun partTwo(): Int {
        return findTotals() - 1
    }

}

fun main(args: Array<String>) {
    val solver = DaySeven2020(fileAsList("day7_2020.txt"))
    println(solver.partOne()) // 131
    println(solver.partTwo()) // 11261
}
