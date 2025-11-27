package aoc.y2021
import aoc.lib.Point
import aoc.lib.Resources.fileAsString
import java.lang.StringBuilder

data class Instruction(val dim: Char, val amount: Int)

typealias Paper = Set<Point>

class DayThirteen(private val input: String) {

    val paper: Paper = input.toGrid()

    val instructions = listOf(
        Instruction('x', 655),
        Instruction('y', 447),
        Instruction('x', 327),
        Instruction('y', 223),
        Instruction('x', 163),
        Instruction('y', 111),
        Instruction('x', 81),
        Instruction('y', 55),
        Instruction('x', 40),
        Instruction('y', 27),
        Instruction('y', 13),
        Instruction('y', 6),
    )

    fun Paper.fold(dim: Char, creaseAt: Int): Paper {
        val newPaper = mutableSetOf<Point>()
        when (dim) {
            'y' -> {
                val (below, above) = partition { it.y > creaseAt }
                below.forEach {
                    newPaper.add(Point(it.x, creaseAt + (creaseAt - it.y)))
                }
                newPaper.addAll(above)
            }
            'x' -> {
                val (after, before) = partition { it.x > creaseAt }
                after.forEach {
                    newPaper.add(Point(creaseAt + (creaseAt - it.x), it.y))
                }
                newPaper.addAll(before)
            }
        }
        return newPaper
    }

    fun partOne(): Int {
        val folded = instructions.fold(paper) { paper, instr ->
            paper
                .fold(instr.dim, instr.amount)
        }
        return folded.size
    }

    fun partTwo() {
        val folded = instructions.fold(paper) { paper, instr ->
            paper
                .fold(instr.dim, instr.amount)
        }
        println(folded)
    }

    fun Paper.print(): String {
        val str = StringBuilder()
        val max_x = this.maxByOrNull { it.x }?.x ?: 0
        val max_y = this.maxByOrNull { it.y }?.y ?: 0

        (0..max_y).map { y ->
            (0..max_x).map { x ->
                if (this.contains(Point(x, y))) {
                    str.append("#")
                } else {
                    str.append(".")
                }
            }
            str.append(".")
            str.append("\n")
        }
        return str.toString()
    }

    private fun String.toGrid(): Paper = lines().map { line ->
        val split = line.split(",")
        Point(split.first().toInt(), split.last().toInt())
    }.toSet()
}

fun main(args: Array<String>) {
    val input = fileAsString("day13_2021.txt")
    val solver = DayThirteen(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
