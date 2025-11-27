package aoc.y2022

import aoc.lib.Resources.fileAsString
import java.util.*

typealias Cost = Map<Resource, Int>

enum class Resource {
    ORE,
    CLAY,
    OBSIDIAN,
    GEODE,
}

const val TIME_ALLOWED = 24

class DayNineteen(private val input: String) {

    data class State(
        var currentTime: Int = TIME_ALLOWED,
        var ore: Int = 0,
        var clay: Int = 0,
        var obsidian: Int = 0,
        var geodes: Int = 0,
        var oreRobots: Int = 1,
        var clayRobots: Int = 0,
        var obsidianRobots: Int = 0,
        var geodeRobots: Int = 0
    ) : Comparable<State> {

        fun canBuild(costs: Cost): Boolean {
            for (cost in costs) {
                when (cost.key) {
                    Resource.ORE -> if (ore < cost.value) return false
                    Resource.CLAY -> if (clay < cost.value) return false
                    Resource.OBSIDIAN -> if (obsidian < cost.value) return false
                    else -> throw UnsupportedOperationException("Cost ${cost.key} is not recognized")
                }
            }
            return true
        }

        fun purchase(costs: Cost) {
            for (cost in costs) {
                when (cost.key) {
                    Resource.ORE -> ore -= cost.value
                    Resource.CLAY -> clay -= cost.value
                    Resource.OBSIDIAN -> obsidian -= cost.value
                    else -> throw UnsupportedOperationException("Cost ${cost.key} is not recognized")
                }
            }
        }

        fun buildRobot(resource: Resource, cost: Cost): State {
            val newState = this.copy()
            when (resource) {
                Resource.ORE -> {
                    newState.oreRobots = oreRobots + 1
                }
                Resource.CLAY -> {
                    newState.clayRobots = clayRobots + 1
                }
                Resource.OBSIDIAN -> {
                    newState.obsidianRobots = obsidianRobots + 1
                }
                Resource.GEODE -> {
                    println("something")
                    newState.geodeRobots = geodeRobots + 1
                }
            }
            newState.purchase(cost)
            return newState
        }

        fun collect() {
            currentTime -= 1
            ore += oreRobots
            clay += clayRobots
            obsidian += obsidianRobots
            geodes += geodeRobots
        }

        override fun compareTo(other: State): Int = other.geodes.compareTo(geodes)
    }

    data class Blueprint(
        val id: Int,
        val oreCost: Cost,
        val clayCost: Cost,
        val obsidianCost: Cost,
        val geodeCost: Cost
    ) {
        //  to construct any type of robot, although it consumes
        //  the necessary resources available when construction begins.
        fun tick(state: State): List<State> {
            val nextStates = mutableListOf<State>()

            // 1. do nothing
            nextStates.add(state.copy())

            // 2. build an ore robot
            if (state.canBuild(this.oreCost)) {
                nextStates.add(state.buildRobot(Resource.ORE, oreCost))
            }

            // 3. clay
            if (state.canBuild(this.clayCost)) {
                nextStates.add(state.buildRobot(Resource.CLAY, clayCost))
            }

            // 4. obsidian
            if (state.canBuild(this.obsidianCost)) {
                nextStates.add(state.buildRobot(Resource.OBSIDIAN, obsidianCost))
            }

            // 5. geode
            if (state.canBuild(this.geodeCost)) {
                nextStates.add(state.buildRobot(Resource.GEODE, geodeCost))
            }

            // collect from existing robots, we want to collect for every option
            for (next in nextStates) {
                next.collect()
            }

            return nextStates
                .filter { it.currentTime <= TIME_ALLOWED }
                .filter { it.currentTime >= 0 }
        }
    }

    // Determine the quality level of each blueprint by multiplying that blueprint's ID number
    fun qualityLevel(id: Int, geodes: Int) = id * geodes

    val blueprints = listOf(
        Blueprint(
            id = 1,
            oreCost = mapOf(Resource.ORE to 4),
            clayCost = mapOf(Resource.ORE to 2),
            obsidianCost = mapOf(Resource.ORE to 3, Resource.CLAY to 14),
            geodeCost = mapOf(Resource.ORE to 2, Resource.OBSIDIAN to 7)
        ),
        Blueprint(
            id = 2,
            oreCost = mapOf(Resource.ORE to 2),
            clayCost = mapOf(Resource.ORE to 3),
            obsidianCost = mapOf(Resource.ORE to 3, Resource.CLAY to 8),
            geodeCost = mapOf(Resource.ORE to 3, Resource.OBSIDIAN to 12)
        )
    )

    private fun geodesFinder(blueprint: Blueprint): Int {
        var maxGeodes = 0
        val queue = PriorityQueue<State>().apply { add(State()) }

        while (queue.isNotEmpty()) {
            val state = queue.poll()
            queue.addAll(blueprint.tick(state))
            maxGeodes = maxOf(maxGeodes, state.geodes)
        }
        return maxGeodes
    }

    fun partOne(): Int {
        val blueprint = blueprints[0]
        return geodesFinder(blueprint)
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day19_2022.txt")
    val solver = DayNineteen(input)
    println(solver.partOne())
}
