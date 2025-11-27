package aoc.y2022

import aoc.lib.Resources.fileAsList

data class Instruction(val count: Int, val from: Int, val to: Int)

class DayFive(private val input: List<String>) {

//    [G]                 [D] [R]
//    [W]         [V]     [C] [T] [M]
//    [L]         [P] [Z] [Q] [F] [V]
//    [J]         [S] [D] [J] [M] [T] [V]
//    [B]     [M] [H] [L] [Z] [J] [B] [S]
//    [R] [C] [T] [C] [T] [R] [D] [R] [D]
//    [T] [W] [Z] [T] [P] [B] [B] [H] [P]
//    [D] [S] [R] [D] [G] [F] [S] [L] [Q]
//    1   2   3   4   5   6   7   8   9

    fun String.parse(): Instruction {
        val s = this.split(" ")
        return Instruction(
            count = s[1].toInt(),
            from = s[3].toInt(),
            to = s[5].toInt()
        )
    }

    //    val stacks = mutableMapOf(
//        1 to mutableListOf('Z', 'N'),
//        2 to mutableListOf('M', 'C', 'D'),
//        3 to mutableListOf('P')
//    )
    val stacks = mutableMapOf(
        1 to mutableListOf('D', 'T', 'R', 'B', 'J', 'L', 'W', 'G'),
        2 to mutableListOf('S', 'W', 'C'),
        3 to mutableListOf('R', 'Z', 'T', 'M'),
        4 to mutableListOf('D', 'T', 'C', 'H', 'S', 'P', 'V'),
        5 to mutableListOf('G', 'P', 'T', 'L', 'D', 'Z'),
        6 to mutableListOf('F', 'B', 'R', 'Z', 'J', 'Q', 'C', 'D'),
        7 to mutableListOf('S', 'B', 'D', 'J', 'M', 'F', 'T', 'R'),
        8 to mutableListOf('L', 'H', 'R', 'B', 'T', 'V', 'M'),
        9 to mutableListOf('Q', 'P', 'D', 'S', 'V')
    )

    fun move(count: Int, from: Int, to: Int) {
        repeat(count) {
            stacks.get(to)?.add(stacks.get(from)!!.last())
            stacks.get(from)?.removeLast()
        }
    }

    fun move9001(count: Int, from: Int, to: Int) {
        val crates = stacks.get(from)?.takeLast(count)
        stacks.get(to)?.addAll(crates!!)
        repeat(count) {
            stacks.get(from)?.removeLast()
        }
    }

    fun partOne(): String {
        val solution = mutableListOf<Char>()
        val instructions = input.map { instruction ->
            instruction.parse()
        }
        for (instruction in instructions) {
            move(instruction.count, instruction.from, instruction.to)
        }
        stacks.values.map {
            solution.add(it.last())
        }
        return solution.joinToString("")
    }

    fun partTwo(): String {
        val solution = mutableListOf<Char>()
        val instructions = input.map { instruction ->
            instruction.parse()
        }
        for (instruction in instructions) {
            move9001(instruction.count, instruction.from, instruction.to)
        }
        stacks.values.map {
            solution.add(it.last())
        }
        return solution.joinToString("")
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day05_2022.txt")
    val solver = DayFive(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
