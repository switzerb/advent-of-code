package aoc.y2021
import aoc.lib.Resources.fileAsList

data class Step(val command: String, val amount: Int)

class DayTwo(private val input: List<String>) {

    fun parse(): List<Step> {
        return input.map { line ->
            line.split(" ")
        }.map { step ->
            Step(command = step[0], amount = step[1].toInt())
        }
    }

    fun partOne(): Int {
        var position = 0
        var depth = 0

        val parsedInput = parse()

        parsedInput.map { step ->
            when (step.command) {
                "forward" -> position += step.amount
                "down" -> depth += step.amount
                "up" -> depth -= step.amount
            }
        }
        return position * depth
    }

    fun partTwo(): Int {
        var position = 0
        var depth = 0
        var aim = 0

        val parsedInput = parse()

        parsedInput.map { step ->
            when (step.command) {
                "forward" -> {
                    position += step.amount
                    depth += (aim * step.amount)
                }
                "down" -> aim += step.amount
                "up" -> aim -= step.amount
            }
        }
        return position * depth
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("day02_2021.txt")
    val solver = DayTwo(input)
    println(solver.partOne()) // 1698735
    println(solver.partTwo()) // 1594785890
}
