package aoc.y2020

import aoc.lib.getInput

data class Instruction(val type: String, val amount: Int ) {}

class DayEight2020(val input: String) {

    private val instr = parseInput()

    private fun parseInput(): List<Instruction> {
        val lines = input.trim().lines()
        return lines.map {
            val parts = it.split(" ")
            Instruction(parts.first(), parts.last().toInt())
        }
    }

    fun partOne() : Int {
        return runInstructions()
    }

    private fun runInstructions() : Int {
        var curr = 0 //index of list
        var accVal = 0
        val visited = mutableListOf<Int>() // list of index values we have visited

        while (true) {
            if (visited.contains(curr)) return accVal
            visited.add(curr)
            val current = instr[curr]
            when(current.type) {
                "acc" -> {
                    accVal = acc(current.amount, accVal)
                    curr++
                }
                "jmp" -> curr = jmp(current.amount, curr)
                "nop" -> curr++
                else -> curr++
            }
        }
    }

    // I take an amount and return a new current position
    private fun jmp(amount: Int, curr: Int) : Int {
        return curr + amount
    }

    // I take an instruction amount and return a new accumulated value
    private fun acc(amount: Int, accVal: Int) : Int {
        return accVal + amount
    }
}

fun main(args: Array<String>) {
    val input = getInput<DayEight2020>("day8_2020.txt")
    val solver = DayEight2020(input)
    println(solver.partOne()) //1709
}
