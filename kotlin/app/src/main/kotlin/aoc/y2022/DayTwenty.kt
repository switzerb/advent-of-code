package aoc.y2022

import aoc.lib.Resources.fileAsString

data class MixNumber(val idx: Int, val value: Long)

class DayTwenty(private val input: String) {

    val decryptionKey = 811589153

    val numbers: MutableList<MixNumber> =
        input
            .split("\n")
            .mapIndexed { idx, value ->
                MixNumber(idx, value.toLong())
            }.toMutableList()

    val appliedNumbers: MutableList<MixNumber> =
        input
            .split("\n")
            .mapIndexed { idx, value ->
                MixNumber(idx, value.toLong() * decryptionKey)
            }.toMutableList()

    fun mix(numbers: MutableList<MixNumber>) {
        numbers.indices.forEach { idx ->
            val newIndex = numbers.indexOfFirst { it.idx == idx }
            val value = numbers.removeAt(newIndex)
            numbers.add((newIndex + value.value).mod(numbers.size), value)
        }
    }

    fun coords(numbers: MutableList<MixNumber>): Long {
        val zero = numbers.indexOfFirst { it.value == 0L }
        val idxOfInterest = listOf(1000, 2000, 3000)
        return idxOfInterest.sumOf { nums -> numbers[(zero + nums) % numbers.size].value }
    }

    fun partOne(): Long {
        mix(numbers)
        return coords(numbers)
    }

    fun partTwo(): Long {
        repeat(10) {
            mix(appliedNumbers)
        }
        return coords(appliedNumbers)
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day20_2022.txt")
    val solver = DayTwenty(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
