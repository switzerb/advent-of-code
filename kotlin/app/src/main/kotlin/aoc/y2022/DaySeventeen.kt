package aoc.y2022

import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString
import kotlin.math.abs

/**
 * --- Day 17: Pyroclastic Flow ---
 * https://adventofcode.com/2022/day/17
 */

const val CHAMBER_WIDTH = 7

enum class Shape {
    HLINE,
    DIAMOND,
    DEL,
    VLINE,
    SQUARE
}

data class Cache(val topography: List<Int>, val blocks: Int, val jets: Int)

data class Meta(val totalBlocks: Int, val height: Int)

data class Rock(
    val shape: Shape,
    val coords: List<Position>,
    val tall: Int
) {
    fun move(p: Position): Rock {
        val newPositions = buildList {
            for (pos in coords) {
                add(pos.plus(p))
            }
        }
        return copy(coords = newPositions)
    }

    fun right(): Rock = this.move(Position(1, 0))
    fun left(): Rock = this.move(Position(-1, 0))
    fun down(): Rock = this.move(Position(0, 1))
}

class DaySeventeen(private val input: String) {
    val atRest: Grid2D = Grid2D()
    var height = 0
    var rockIdx = 0
    var jetIdx = 0

    private val rockSequence = listOf(
        Rock(
            shape = Shape.HLINE,
            coords = listOf(Position(0, 0), Position(1, 0), Position(2, 0), Position(3, 0)),
            tall = 1
        ),
        Rock(
            shape = Shape.DIAMOND,
            coords = listOf(Position(1, 0), Position(0, -1), Position(1, -1), Position(2, -1), Position(1, -2)),
            tall = 3
        ),
        Rock(
            shape = Shape.DEL,
            coords = listOf(Position(0, 0), Position(1, 0), Position(2, 0), Position(2, -1), Position(2, -2)),
            tall = 3
        ),
        Rock(
            shape = Shape.VLINE,
            coords = listOf(Position(0, 0), Position(0, -1), Position(0, -2), Position(0, -3)),
            tall = 4
        ),
        Rock(
            shape = Shape.SQUARE,
            coords = listOf(Position(0, 0), Position(1, 0), Position(0, -1), Position(1, -1)),
            tall = 2
        )
    )
    val jets = input.toCharArray()

    fun couldMove(rock: Rock) =
        rock.coords.all {
            it.x in 0..6 &&
                it.y <= 0 &&
                !atRest.grid.contains(it)
        }

    fun draw(rock: Rock? = null) =
        buildString {
            for (y in atRest.minY() - 5..0) {
                for (x in -1..CHAMBER_WIDTH) {
                    if (x < 0 || x == CHAMBER_WIDTH) append('|')
                    else if (atRest.grid.contains(Position(x, y))) append('#')
                    else if (rock == null) append('.')
                    else if (rock.coords.contains(Position(x, y))) append('@')
                    else append('.')
                }
                append('\n')
            }
            append('+')
            append("-".repeat(CHAMBER_WIDTH))
            append('+')
            append('\n')
        }

    private fun topography(coords: List<Position>): List<Int> =
        coords.groupBy { it.x }
            .entries
            .sortedBy { it.key }
            .map { pointList -> pointList.value.minByOrNull { p -> p.y } }
            .let {
                val top = atRest.minY()
                it.map { p -> top - p!!.y }
            }

    private fun calculateHeight(): Long {
        val rounds = 1000000000000L - 1
        val visited: MutableMap<Cache, Meta> = mutableMapOf()
        while (true) {
            run()
            val cache = Cache(
                topography(atRest.grid),
                rockIdx % rockSequence.size,
                jetIdx % jets.size
            )
            if (cache in visited) {
                val (startBlocks, startHeight) = visited.getValue(cache)
                val blocks = rockIdx - 1L - startBlocks
                val totalLoops = (rounds - startBlocks) / blocks
                val remaining = (rounds - startBlocks) - (totalLoops * blocks)
                val delta = abs(height) - startHeight
                repeat(remaining.toInt()) {
                    run()
                }
                return abs(height) + (delta * (totalLoops - 1))
            }
            visited[cache] = Meta(rockIdx - 1, abs(height))
        }
    }

    private fun run() {
        val nextRock = rockSequence[rockIdx++ % Shape.values().size]
        var rock = nextRock.move(Position(2, height - 3))
        while (true) {
            val motion = jets[jetIdx++ % jets.size]

            val pushed = when (motion) {
                '<' -> rock.left()
                '>' -> rock.right()
                else -> throw UnsupportedOperationException("Motion not supported")
            }
            if (couldMove(pushed)) rock = pushed
            val dropped = rock.down()
            if (couldMove(dropped)) {
                rock = dropped
            } else {
                atRest.addTo(rock.coords.toList())
                height = if (height == 0) -rock.tall else atRest.minY() - 1
                break
            }
        }
    }

    fun partOne(rounds: Int): Int {
        repeat(rounds) {
            run()
        }
        return abs(height)
    }

    fun partTwo(): Long = calculateHeight()
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day17_2022.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne(rounds = 2022)) // 3147
    println(solver.partTwo())
}
