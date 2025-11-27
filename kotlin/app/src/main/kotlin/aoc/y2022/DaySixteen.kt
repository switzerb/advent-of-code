package aoc.y2022

import aoc.lib.Resources.fileAsString
import com.github.shiguruikai.combinatoricskt.combinations
import com.github.shiguruikai.combinatoricskt.permutations

const val START = "AA"
const val RANDOM_LARGE_NUMBER = 99999

typealias PathMap = Map<String, MutableMap<String, Int>>

data class Valve(val name: String, val paths: List<String>, val flow: Int) {
    companion object {
        fun parse(input: String): Valve =
            Valve(
                input.substringAfter(" ").substringBefore(" "),
                input.substringAfter("valve").substringAfter(" ").split(", "),
                input.substringAfter("=").substringBefore(";").toInt()
            )
    }
}

class DaySixteen(private val input: String) {
    private val valves: Map<String, Valve> =
        input.split("\n")
            .map(Valve::parse)
            .associateBy { it.name }

    val paths: PathMap = buildPaths()

    private fun searchPaths(
        location: String,
        timeAllowed: Int,
        visited: Set<String> = emptySet(),
        elapsedTime: Int = 0,
        totalFlow: Int = 0
    ): Int = paths
        .getValue(location)
        .asSequence()
        .filterNot { (next, _) -> next in visited }
        .filter { (_, minutes) -> elapsedTime + minutes + 1 < timeAllowed }
        .maxOfOrNull { (nextRoom, minutes) ->
            searchPaths(
                nextRoom,
                timeAllowed,
                visited + nextRoom,
                elapsedTime + minutes + 1,
                totalFlow + ((timeAllowed - elapsedTime - minutes - 1) * valves.getValue(nextRoom).flow)
            )
        } ?: totalFlow

    fun buildPaths(): PathMap {
        val paths: PathMap = valves
            .values.associate {
                it.name to it.paths.associateWith { 1 }.toMutableMap()
            }.toMutableMap()

        paths.keys
            .permutations(3)
            .forEach { (between, from, to) ->
                paths[from, to] = minOf(
                    paths[from, to],
                    paths[from, between] + paths[between, to]
                )
            }

        // we want to ignore zero flow rooms
        val noFlow = valves
            .values
            .filter { it.flow == 0 || it.name == START }
            .map { it.name }.toSet()

        paths
            .values
            .forEach { it.keys.removeAll(noFlow) }

        val toStart: Set<String> = paths.getValue(START).keys
        return paths.filter { it.key in toStart || it.key == START }
    }

    operator fun PathMap.set(k1: String, k2: String, value: Int) {
        getValue(k1)[k2] = value
    }

    operator fun PathMap.get(k1: String, k2: String): Int = get(k1)?.get(k2) ?: RANDOM_LARGE_NUMBER

    fun partOne(timeAllowed: Int): Int = searchPaths(START, timeAllowed)

    fun partTwo(timeAllowed: Int): Int =
        paths.keys.filter { it != START }
            .combinations(paths.size / 2)
            .map { it.toSet() }
            .maxOf { half ->
                searchPaths(START, 26, half) + searchPaths(START, timeAllowed, paths.keys - half)
            }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day16_2022.txt")
    val solver = DaySixteen(input)
    println(solver.partOne(30)) // 1617
    println(solver.partTwo(26)) // 1617
}
