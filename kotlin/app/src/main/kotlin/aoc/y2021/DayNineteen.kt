package aoc.y2021

import aoc.lib.Grid3D
import aoc.lib.Resources.fileAsString
import aoc.lib.Vector

typealias Beacon = Vector

data class Scanner(val beacons: List<Beacon>) {
    private val region = Grid3D()

    init {
        region.grid = beacons
    }

    override fun toString(): String {
        return region.print2D()
    }
}

class DayNineteen(private val input: String) {

    fun partOne(): String {
        val scanners: List<Scanner> = listOf(
            Scanner(
                listOf(
                    Beacon(0, 2),
                    Beacon(4, 1),
                    Beacon(3, 3)
                )
            ),
            Scanner(
                listOf(
                    Beacon(-1, -1),
                    Beacon(-5, 0),
                    Beacon(-2, 1)
                )
            )
        )

        println(scanners[0])
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day19_2021.txt")
    val solver = DayNineteen(input)
    println(solver.partOne())
}
