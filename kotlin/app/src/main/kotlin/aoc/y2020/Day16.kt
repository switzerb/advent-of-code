package aoc.y2020
import aoc.lib.Resources.fileAsList

data class Ticket(val fields: List<Int>) {
    fun getInvalidFields(rules: List<IntRange>): List<Int> {
        return fields.filter { field ->
            rules.none { rule -> field in rule }
        }
    }

    fun isValid(rules: List<IntRange>): Boolean {
        return fields.all { field ->
            rules.any { rule -> field in rule}
        }
    }

}

class Day162020(val input: List<String>) {

    private val rules: Map<String,List<IntRange>> = parseRules()
    private val allRules: List<IntRange> = rules.values.flatten()
    private val mine: List<Int> = parseMine()
    private val nearby: List<Ticket> = parseNearby()

    fun partOne(): Int {
        return nearby.sumBy { ticket -> ticket.getInvalidFields(allRules).sum() }
    }

//    Filter nearby tickets to only valid ones
//    For every field name, come up with a list of ticket positions that ALL pass one of the rules.
//      Then fold?
//        - Find fields that only have one possible matching position
//        - Remove that position from all other matching sets?
//        - what if we can't reduce to a deterministic set??
//     Get the product of the “departure” fields on our ticket.

    fun partTwo(): Long {
        val fieldRules = reduceRules(getPotentialRules())
        return getDepartures(fieldRules)
    }

    fun getValidTickets(): List<Ticket> = nearby.filter { ticket ->  ticket.isValid(allRules) }

    fun getPotentialRules() : Map<String, MutableSet<Int>> {
        return rules.keys.map { rule ->
            rule to mine.indices.filter { position ->
                positionPassesRule(getValidTickets(), position, rule)
            }.toMutableSet()
        }.toMap()
    }

    fun getDepartures(fieldRules: Map<String,Int>) : Long {
        return fieldRules.entries
            .filter { it.key.startsWith("departure") }
            .map { mine[it.value].toLong() }
            .reduce { a, b -> a * b }
    }

    private fun reduceRules(possibleRules: Map<String,MutableSet<Int>>): Map<String,Int> {
        val fieldRules = mutableMapOf<String,Int>()
        while(fieldRules.size < possibleRules.size) {
            possibleRules.entries
                .filter { (_, possibleValues) -> possibleValues.size == 1 }
                .forEach { (rule, possibleValues) ->
                    val position = possibleValues.first()
                    fieldRules[rule] = position
                    possibleRules.values.forEach { it.remove(position) }
                }
        }
        return fieldRules
    }

    fun positionPassesRule(tickets: List<Ticket>, position: Int, fieldName: String): Boolean {
        return tickets.all { ticket ->
            rules.getValue(fieldName).any { rule -> ticket.fields[position] in rule }
        }
    }

    private fun parseRules(): Map<String,List<IntRange>> {
        return input.takeWhile { it.isNotEmpty() }
            .map { line ->
                val (name, s1, e1, s2, e2) = matcher.matchEntire(line)!!.destructured
                name to listOf(
                    s1.toInt()..e1.toInt(),
                    s2.toInt()..e2.toInt()
                )
            }.toMap()
    }

    private fun parseMine(): List<Int> {
        return input
            .dropWhile { it != "your ticket:" }
            .drop(1)
            .first()
            .split(",")
            .map { it.toInt() }
    }

    private fun parseNearby(): List<Ticket> = input
        .dropWhile { it != "nearby tickets:" }
        .drop(1)
        .map { row ->
            Ticket(row.split(",").map { it.toInt() })
        }

    companion object {
        private val matcher =  """^([a-z ]+): (\d+)-(\d+) or (\d+)-(\d+)$""".toRegex()
    }
}

fun main(args: Array<String>) {
    val solver = Day162020(fileAsList("day16_2020.txt")) //28882
    println(solver.partOne()) //28882
    println(solver.partTwo()) //1429779530273
}
