package aoc.y2021
import aoc.lib.Resources.fileAsString
import java.lang.Math.abs

class DaySeven(private val input: String) {

    val max = parse().maxOrNull() ?: 0
    val min = parse().minOrNull() ?: 0

    fun parse(): List<Int> {
        return input
            .split(",")
            .map {
                it.toInt()
            }
    }

    fun costOfFuel(position: Int): Int {
        return parse().fold(0) { fuel, crab ->
            abs(crab - position) + fuel
        }
    }

    fun costOfExponentialFuel(position: Int): Int {
        return parse().fold(0) { fuel, crab ->
            val diff = abs(crab - position)
            (1..diff).sum() + fuel
        }
    }

    fun partOne(): Int {
        val costs = mutableListOf<Int>()
        (min..max).forEach { position ->
            costs.add(costOfFuel(position))
        }
        return costs.minOrNull() ?: 0
    }

    fun partTwo(): Int {
        val costs = mutableListOf<Int>()
        (min..max).forEach { position ->
            costs.add(costOfExponentialFuel(position))
        }
        return costs.minOrNull() ?: 0
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day07_2021.txt")
    val solver = DaySeven(input)
    println(solver.partOne())
    println(solver.partTwo())
}
