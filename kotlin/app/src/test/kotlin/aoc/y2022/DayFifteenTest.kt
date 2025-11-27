package aoc.y2022

import aoc.lib.Position
import org.junit.Test
import kotlin.test.assertEquals

class DayFifteenTest {

    val sensors = listOf<Sensor>(
        Sensor(sensor = Position(2, 18), closestBeacon = Position(-2, 15)),
        Sensor(sensor = Position(9, 16), closestBeacon = Position(10, 16)),
        Sensor(sensor = Position(13, 2), closestBeacon = Position(15, 3)),
        Sensor(sensor = Position(12, 14), closestBeacon = Position(10, 16)),
        Sensor(sensor = Position(10, 20), closestBeacon = Position(10, 16)),
        Sensor(sensor = Position(14, 17), closestBeacon = Position(10, 16)),
        Sensor(sensor = Position(8, 7), closestBeacon = Position(2, 10)),
        Sensor(sensor = Position(2, 0), closestBeacon = Position(2, 10)),
        Sensor(sensor = Position(0, 11), closestBeacon = Position(2, 10)),
        Sensor(sensor = Position(20, 14), closestBeacon = Position(25, 17)),
        Sensor(sensor = Position(17, 20), closestBeacon = Position(21, 22)),
        Sensor(sensor = Position(16, 7), closestBeacon = Position(15, 3)),
        Sensor(sensor = Position(14, 3), closestBeacon = Position(15, 3)),
        Sensor(sensor = Position(20, 1), closestBeacon = Position(15, 3))
    )

    @Test
    fun t1_p1() {
        val solver = DayFifteen(sensors, rowOfInterest = 10)
        assertEquals(26, solver.partOne())
    }

    @Test
    fun t1_p2() {
        val solver = DayFifteen(sensors, rowOfInterest = 10)
        assertEquals(56000011, solver.partTwo())
    }
}
