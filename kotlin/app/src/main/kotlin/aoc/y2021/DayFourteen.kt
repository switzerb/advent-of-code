package aoc.y2021
import aoc.lib.Resources.fileAsString

typealias Histogram = MutableMap<String, Long>
class DayFourteen(private val input: String) {

    val template = input.lines().first().trim()
    val rules: Map<String, String> = input.parseRules()

    fun run(steps: Int): Long {
        var histogram = template.toHistogram().toMutableMap()

        (1..steps).forEach {
            histogram = calculate(histogram)
        }

        val elements = occurances(histogram)
        return elements.values.maxOf { it } - elements.values.minOf { it }
    }

    fun calculate(histogram: Histogram): Histogram {
        val next = mutableMapOf<String, Long>()
        for (pair in histogram.keys) {
            val element = rules[pair]!!
            val left = "${pair.first()}$element"
            val right = "$element${pair.last()}"
            next[left] =
                next.getOrDefault(left, 0) + histogram.getOrDefault(pair, 0)
            next[right] =
                next.getOrDefault(right, 0) + histogram.getOrDefault(pair, 0)
        }
        return next
    }

    fun occurances(histogram: Histogram): Map<Char, Long> {
        val elements = histogram
            .map { listOf(Pair(it.key[0], it.value)) }
            .flatten()
            .groupBy { it.first }
            .mapValues { p -> p.value.sumOf { it.second } }
            .toMutableMap()

        elements[template.first()] = elements.getOrDefault(template.first(), 0) + 1
        elements[template.last()] = elements.getOrDefault(template.last(), 0) + 1
        return elements
    }

    fun partOne(): Long {
        return run(10)
    }

    fun partTwo(): Long = run(40)

    private fun String.toHistogram() = windowed(2, 1)
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }

    private fun String.parseRules() = lines()
        .drop(2)
        .associate { line ->
            val (key, value) = line.split(" -> ")
            key to value
        }
}

fun main(args: Array<String>) {
    val input = fileAsString("day14_2021.txt")
    val solver = DayFourteen(input)
    println(solver.partOne()) // 2549
    println(solver.partTwo()) // 2516901104210
}
