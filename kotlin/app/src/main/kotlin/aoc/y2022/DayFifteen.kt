package aoc.y2022

import aoc.lib.Position
import java.lang.Math.abs

data class Sensor(val sensor: Position, val closestBeacon: Position) {
    val sensorRange = sensor.getManhattanDistance(closestBeacon)
    fun positionsAtRow(rowOfInterest: Int): List<Position> {
        val spentRange = abs(sensor.y - rowOfInterest)
        val remainder = sensorRange - spentRange
        val minX = sensor.x - remainder
        val maxX = sensor.x + remainder
        return Position(minX, rowOfInterest).lineTo(Position(maxX, rowOfInterest))
    }

    fun positionsAtEdge(max: Int): List<Position> {
        val whatever = mutableListOf<Position>()
        val top = Position(sensor.x, sensor.y - sensorRange - 1)
        val bottom = Position(sensor.x, sensor.y + sensorRange + 1)
        val left = Position(sensor.x - sensorRange - 1, sensor.y)
        val right = Position(sensor.x + sensorRange + 1, sensor.y)
        /**
         *   #
         *  ###
         *   #
         */
        whatever.addAll(left.lineTo(top))
        whatever.addAll(top.lineTo(right))
        whatever.addAll(right.lineTo(bottom))
        whatever.addAll(bottom.lineTo(left))
        return whatever.filter { p -> p.x >= 2000000 && p.y <= max && p.y >= 2000000 && p.x <= max }
    }

    fun isInSensorRange(other: Position) = sensor.getManhattanDistance(other) <= sensorRange
}

class DayFifteen(private val sensors: List<Sensor>, val rowOfInterest: Int = 10) {
    fun partOne(): Int {
        val positionSet = buildSet {
            sensors.map { sensor ->
                addAll(sensor.positionsAtRow(rowOfInterest))
            }
        }
        return positionSet
            .filter { position -> position !in sensors.map { sensor -> sensor.closestBeacon } }
            .filter { position -> position !in sensors.map { sensor -> sensor.sensor } }
            .size
    }

    fun partTwo(): Long {
        val candidates = buildSet {
            sensors.map { sensor ->
                addAll(sensor.positionsAtEdge(max = 4000000))
            }
        }
//        println(candidates)
        val remaining = candidates.toMutableSet()
        for (candidate in candidates) {
            for (sensor in sensors) {
                if (sensor.isInSensorRange(candidate)) {
                    remaining.remove(candidate)
                }
            }
        }
        val beacon = remaining.firstOrNull() ?: throw Error("I failed.")
        return beacon.x.toLong() * 4000000 + beacon.y.toLong()
    }
}

fun main(args: Array<String>) {
    val sensors = listOf(
        Sensor(sensor = Position(545406, 2945484), closestBeacon = Position(772918, 2626448)),
        Sensor(sensor = Position(80179, 3385522), closestBeacon = Position(772918, 2626448)),
        Sensor(sensor = Position(2381966, 3154542), closestBeacon = Position(2475123, 3089709)),
        Sensor(sensor = Position(2607868, 1728571), closestBeacon = Position(2715626, 2000000)),
        Sensor(sensor = Position(746476, 2796469), closestBeacon = Position(772918, 2626448)),
        Sensor(sensor = Position(911114, 2487289), closestBeacon = Position(772918, 2626448)),
        Sensor(sensor = Position(2806673, 3051666), closestBeacon = Position(2475123, 3089709)),
        Sensor(sensor = Position(1335361, 3887240), closestBeacon = Position(2505629, 4282497)),
        Sensor(sensor = Position(2432913, 3069935), closestBeacon = Position(2475123, 3089709)),
        Sensor(sensor = Position(1333433, 35725), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(2289207, 1556729), closestBeacon = Position(2715626, 2000000)),
        Sensor(sensor = Position(2455525, 3113066), closestBeacon = Position(2475123, 3089709)),
        Sensor(sensor = Position(3546858, 3085529), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(3542939, 2742086), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(2010918, 2389107), closestBeacon = Position(2715626, 2000000)),
        Sensor(sensor = Position(3734968, 3024964), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(2219206, 337159), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(1969253, 890542), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(3522991, 3257032), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(2303155, 3239124), closestBeacon = Position(2475123, 3089709)),
        Sensor(sensor = Position(2574308, 111701), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(14826, 2490395), closestBeacon = Position(772918, 2626448)),
        Sensor(sensor = Position(3050752, 2366125), closestBeacon = Position(2715626, 2000000)),
        Sensor(sensor = Position(3171811, 2935106), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(3909938, 1033557), closestBeacon = Position(3493189, -546524)),
        Sensor(sensor = Position(1955751, 452168), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(2159272, 614653), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(3700981, 2930103), closestBeacon = Position(3629407, 2984857)),
        Sensor(sensor = Position(3236266, 3676457), closestBeacon = Position(3373823, 4223689)),
        Sensor(sensor = Position(3980003, 3819278), closestBeacon = Position(3373823, 4223689)),
        Sensor(sensor = Position(1914391, 723058), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(474503, 1200604), closestBeacon = Position(-802154, 776650)),
        Sensor(sensor = Position(2650714, 3674470), closestBeacon = Position(2505629, 4282497)),
        Sensor(sensor = Position(1696740, 586715), closestBeacon = Position(1929144, 529341)),
        Sensor(sensor = Position(3818789, 2961752), closestBeacon = Position(3629407, 2984857))
    )
    val solver = DayFifteen(sensors, rowOfInterest = 2000000)
//    println(solver.partOne()) // 5367037
    println(solver.partTwo())
}
