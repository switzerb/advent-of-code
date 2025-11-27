package aoc.y2021
import aoc.lib.Resources.fileAsList

typealias BingoBoard = List<List<Int>>

class DayFour(input: List<String>) {

    private val draws: List<Int> = input.first().split(",").map { it.toInt() }
    private val boards: Set<BingoBoard> = parseInput(input)

    fun partOne(): Int {
        val drawn = draws.take(4).toMutableSet()
        return draws.drop(4).firstNotNullOf { draw ->
            drawn += draw
            boards.firstOrNull { it.isWinner(drawn) }?.let { winner ->
                draw * winner.sumUnmarked(drawn)
            }
        }
    }

    fun partTwo(): Int {
        val drawn = draws.toMutableSet()
        return draws.reversed().firstNotNullOf { draw ->
            drawn -= draw
            boards.firstOrNull { !it.isWinner(drawn) }?.let { winner ->
                draw * (winner.sumUnmarked(drawn) - draw)
            }
        }
    }

    private fun BingoBoard.isWinner(draws: Set<Int>) =
        this.any { row -> row.all { it in draws } } ||
            (0..4).any { col -> this.all { row -> row[col] in draws } }

    private fun BingoBoard.sumUnmarked(draws: Set<Int>): Int =
        this.sumOf { row ->
            row.filterNot { it in draws }.sum()
        }

    private fun parseInput(input: List<String>): Set<BingoBoard> =
        input
            .asSequence()
            .drop(1)
            .filter { it.isNotEmpty() }
            .chunked(5)
            .map { parseSingleBoard(it) }
            .toSet()

    private fun parseSingleBoard(input: List<String>): BingoBoard =
        input.map { row ->
            row.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        }
}

fun main(args: Array<String>) {
    val input = fileAsList("day04_2021.txt")
    val solver = DayFour(input)
    println(solver.partOne())
    println(solver.partTwo())
}
