package aoc.y2020

const val ROWS = 127
const val COLS = 7
const val FRONT = 'F'
const val BACK = 'B'
const val RIGHT = 'R'
const val LEFT = 'L'

// enum class Part(val letter: Char) {
//    FRONT('F'),
//    BACK('B'),
//    LEFT('L'),
//    RIGHT('R')
// }

data class Range(val min: Int, val max: Int)

class DayFive2020(val input: String) {

    fun parser(): List<String> {
        return input.trim().lines()
    }

    fun partitionByRegion(part: Char, range: Range): Range {
        val mid = (range.max + range.min) / 2
        if (part == FRONT) {
            return Range(range.min, mid)
        } else if (part == BACK) {
            return Range(mid + 1, range.max)
        }
        return range
    }

    fun partitionbyCol(part: Char, range: Range): Range {
        val mid = (range.max + range.min) / 2
        if (part == LEFT) {
            return Range(range.min, mid)
        } else if (part == RIGHT) {
            return Range(mid + 1, range.max)
        }
        return range
    }

    fun getSeatId(pass: String): Int {
        val region: CharArray = pass.toCharArray()
        val row = region.fold(Range(0, ROWS)) { acc: Range, c: Char ->
            val (min, max) = partitionByRegion(c, acc)
            Range(min, max)
        }
        val col = region.fold(Range(0, COLS)) { acc: Range, c: Char ->
            val (min, max) = partitionbyCol(c, acc)
//            println("min $min, max $max")
            Range(min, max)
        }
        assert(row.max == row.min)
        assert(col.max == col.min)
        return (row.max * 8) + col.max
    }

    fun partOne(): Int {
        val lines = parser()
        val seats = lines
            .map { getSeatId(it) }
            .sorted()
        return seats.maxOrNull() ?: 0
    }

    fun partTwo(): Int {
        val lines = parser()
        val seats = lines
            .map { getSeatId(it) }
            .sorted()
        var count = 100
        for (i in seats.indices) {
            if (count != seats[i]) {
                return seats[i] - 1
            }
            count++
        }
        return 0
    }
}

fun main(args: Array<String>) {
    val cl = DayFive2020::class.java.classLoader.getResource("day5_2020.txt") ?: return
    val input = cl.readText()
    val solver = DayFive2020(input)
    println("part one:" + solver.partOne()) // 861
    println("part two:" + solver.partTwo()) // 633
}
