package aoc.y2022

import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

typealias Elf = Position

class DayTwentyThree(private val input: String) {

    val elves: List<Elf> = input
        .split("\n")
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, char ->
                if (char == '#') Elf(x, y) else null
            }
        }.toList()

    val field = Grid2D(elves)

    private val nexts: List<List<Elf>> = nextDestinations()

    private fun nextDestinations(): List<List<Elf>> =
        listOf(
            listOf(Position(0, -1), Position(-1, -1), Position(1, -1)), // N
            listOf(Position(0, 1), Position(-1, 1), Position(1, 1)), // S
            listOf(Position(-1, 0), Position(-1, -1), Position(-1, 1)), // W
            listOf(Position(1, 0), Position(1, -1), Position(1, 1)) // E
        )

    fun round(elves: List<Elf>, step: Int): List<Elf> {
        val nextPositions = mutableListOf<Elf>()
        nextPositions.addAll(elves)
        val movers: Map<Elf, Elf> = elves
            .filter { elf -> elf.nextToInclusive().any { it in elves } }
            .mapNotNull { elf ->
                nexts.indices.map { direction -> nexts[(step + direction) % 4] }
                    .firstNotNullOfOrNull { offsets ->
                        if (offsets.none { offset -> (elf + offset) in elves }) elf to (elf + offsets.first())
                        else null
                    }
            }.toMap()

        val targets = movers
            .values
            .groupingBy { it }
            .eachCount()
            .filter { it.value == 1 }
            .keys

        movers
            .filter { (_, target) -> target in targets }
            .forEach { (source, target) ->
                nextPositions.remove(source)
                nextPositions.add(target)
            }
        return nextPositions
    }

    fun partOne(): Int {
        val locations = (0 until 10)
            .fold(elves) { positions, step -> round(positions, step) }
        val gridSize =
            ((locations.maxOf { it.x } - locations.minOf { it.x }) + 1) * ((locations.maxOf { it.y } - locations.minOf { it.y }) + 1)
        return gridSize - locations.size
    }

    fun partTwo(): Int {
        var thisTurn = elves
        var roundId = 0
        do {
            val previousTurn = thisTurn
            thisTurn = round(previousTurn, roundId++)
        } while (previousTurn != thisTurn)
        return roundId
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day23_2022.txt")
    val solver = DayTwentyThree(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
