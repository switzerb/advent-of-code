package aoc.y2021
import aoc.lib.Resources.fileAsList

class DayThree(private val input: List<String>, val width: Int = 12) {

    val n = width - 1

    private fun <T> Pair<List<T>, List<T>>.longest(): List<T> =
        if (first.size >= second.size) first else second

    private fun <T> Pair<List<T>, List<T>>.shortest(): List<T> =
        if (first.size < second.size) first else second

    fun filterByBitCriteria(compare: String): String {
        return input
            .first()
            .indices
            .fold(input) { inputs, column ->
                if (inputs.size == 1) inputs else {
                    val split = inputs.partition { it[column] == '1' }
                    if (compare == "more") split.longest() else split.shortest()
                }
            }.first()
    }

    fun histogram(idx: Int): Map<Char, Int> {
        return input
            .groupingBy { it[idx] }
            .eachCount()
    }

    fun getMostCommon(idx: Int): Char {
        val max = histogram(idx).maxByOrNull { it.value }
        return max?.key!!
    }

    fun getLeastCommon(idx: Int): Char {
        val min = histogram(idx).minByOrNull { it.value }
        return min?.key!!
    }

    fun getRate(compare: String): String {
        val rate = mutableListOf<Char>()
        (0..n).map { idx ->
            when (compare) {
                "more" -> rate.add(getMostCommon(idx))
                "less" -> rate.add(getLeastCommon(idx))
                else -> null
            }
        }
        return rate.joinToString("")
    }

    fun partOne(): Int {
        val gammaDecimal = getRate("more").toInt(2)
        val epsilonDecimal = getRate("less").toInt(2)
        return gammaDecimal * epsilonDecimal
    }

    fun partTwo(): Int {
        val oxygenRating = filterByBitCriteria("more")
        val CO2scrubber = filterByBitCriteria("less")
        return oxygenRating.toInt(2) * CO2scrubber.toInt(2)
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("day03_2021.txt")
    val solver = DayThree(input)
    println(solver.partOne()) // 2640986
    println(solver.partTwo())
}
