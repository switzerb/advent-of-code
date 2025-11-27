package aoc.y2020

import aoc.lib.Resources.fileAsList

class Day19(val input: List<String>) {

    private val rules : MutableMap<Int, String> = parse()
    private val memo: MutableMap<Int, String> = mutableMapOf()
    private val messages: List<String> = input.dropWhile { it.isNotBlank() }.drop(1)

    private fun parse(): MutableMap<Int, String> {
        return input.takeWhile { it.isNotBlank() }.map {
            val (k, v) = it.split(": ")
            k.toInt() to v
        }.toMap().toMutableMap()
    }

    private fun lookup(id: Int): String {
        if(!memo.containsKey(id)) {
            val rule = rules.getValue(id)
            val tokens = rule.split(" ")
            val v = tokens.map { token ->
                when {
                    token.startsWith('"') -> token.replace("\"", "")
                    token == "|" -> "|"
                    else -> lookup(token.toInt())
                }
            }
            memo[id] = "(" + v.joinToString("") + ")"
        }
        return memo[id]!!
    }

    fun ruleMatch(message: String): Boolean {
        val rule = lookup(0)
        return Regex(rule).matches(message)
    }

    fun partOne() : Int {
        return messages.filter { ruleMatch(it) }.size
    }

//        8: 42 | 42 8
//        11: 42 31 | 42 11 31
     fun matchPartTwo(message: String): Boolean {
        lookup(0)
        memo[8] = "(" + memo[42] + "+" + ")"
        memo[11] = "(" + memo[42] + memo[31] + "|" + memo[42] + memo[11] + memo[31] + ")"
        val rule = memo[8] + memo[11]
        return Regex(rule).matches(message)
     }

    fun partTwo() : Int {
        return messages.filter { matchPartTwo(it) }.size
    }
}

fun main(args: Array<String>) {
    val solver = Day19(fileAsList("day19_2020.txt"))
    println(solver.partOne()) //132
    println(solver.partTwo())
}
