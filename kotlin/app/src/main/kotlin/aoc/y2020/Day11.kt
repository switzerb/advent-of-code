package aoc.y2020

import aoc.lib.Resources.fileAsString

enum class Seat(val status: String) {
    FLOOR("."),
    OCCUPIED("#"),
    EMPTY("L");

    companion object {
        fun parse(s: String): Seat = when (s) {
            "." -> FLOOR
            "#" -> OCCUPIED
            "L" -> EMPTY
            else -> throw IllegalArgumentException("We don't understand seat '$s'.")
        }
    }

    override fun toString(): String {
        return status
    }
}

data class Point(val r: Int, val c: Int, val step: Int = 1) {
    fun up(): Point = Point(this.r - step, this.c)
    fun down(): Point = Point(this.r + step, this.c)
    fun left(): Point = Point(this.r, this.c - step)
    fun right(): Point = Point(this.r, this.c + step)
    fun upLeft(): Point = this.up().left()
    fun downLeft(): Point = this.down().left()
    fun upRight(): Point = this.up().right()
    fun downRight(): Point = this.down().right()
}

data class SeatGrid(val seats: List<Seat>, val columns: Int, val rows: Int) {

    companion object {
        fun parse(input: String): SeatGrid {
            val columns = input.split("\n").first().length
            val rows = input.length / columns
            val seats = input
                .trim()
                .split("")
                .filter { it != "\n" }
                .filter { it != "" }
                .map { Seat.parse(it) }
            return SeatGrid(seats, columns, rows)
        }
    }

    //  - If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
    //  - If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
    //  - Otherwise, the seat's state does not change.
    private fun getNextSeat(index: Int, seat: Seat): Seat {
        val neighbors = getNeighbors(index);
        if(seat == Seat.EMPTY) {
            if(!neighbors.contains(Seat.OCCUPIED)) {
                return Seat.OCCUPIED
            }
        }
        if(seat == Seat.OCCUPIED) {
            val count = neighbors.filter { it == Seat.OCCUPIED }.size
            if(count >= 4) {
                return Seat.EMPTY
            }
        }
        return seat
    }

    private fun getNextLineofSightSeat(index: Int, seat: Seat): Seat {
        val neighbors = getLineOfSightNeighbors(index);
        if(seat == Seat.EMPTY) {
            if(!neighbors.contains(Seat.OCCUPIED)) {
                return Seat.OCCUPIED
            }
        }
        if(seat == Seat.OCCUPIED) {
            val count = neighbors.filter { it == Seat.OCCUPIED }.size
            if(count >= 5) {
                return Seat.EMPTY
            }
        }
        return seat
    }

    fun tick(): SeatGrid {
        val state = seats.mapIndexed { i,it -> getNextSeat(i,it) }
        return SeatGrid(state, columns, rows)
    }

    fun tock(): SeatGrid {
        val state = seats.mapIndexed { i,it -> getNextLineofSightSeat(i,it) }
        return SeatGrid(state, columns, rows)
    }

    fun isValid(p: Point):Boolean {
        return p.r >= 0 && p.c >= 0 && p.r < rows && p.c < columns
    }

    fun getNeighbors(index: Int) : List<Seat> {
        val point = toPoint(index)
        val points = listOf(
            point.up(),
            point.down(),
            point.left(),
            point.right(),
            point.upLeft(),
            point.upRight(),
            point.downLeft(),
            point.downRight()
        )

        return points
            .filter { isValid(it)}
            .map { fromPoint(it) }
            .map { seats[it] }
    }

    fun findSeat(p: Point, dir: (p: Point) -> Point): Point? {
        var next = dir(p)

        while(isValid(next) && seats[fromPoint(next)] == Seat.FLOOR ) {
            next = dir(next)
        }
        return if(isValid(next)) next else null
    }

    fun getLineOfSightNeighbors(index: Int): List<Seat> {
        val point = toPoint(index)
        val points = listOf(
            findSeat(point) { it.up() },
            findSeat(point) { it.down() },
            findSeat(point) { it.right() },
            findSeat(point) { it.left() },
            findSeat(point) { it.upLeft() },
            findSeat(point) { it.upRight() },
            findSeat(point) { it.downLeft() },
            findSeat(point) { it.downRight() }
        )
        return points
            .filterNotNull()
            .map { fromPoint(it) }
            .map {seats[it] }
    }

    fun toPoint(index: Int): Point {
        val col = index % columns
        val row = index / columns
        return Point(row,col)
    }

    fun fromPoint(p: Point): Int {
        return p.c + columns * p.r;
    }

    fun countOccupied(): Int {
        return seats.filter { it == Seat.OCCUPIED }.size
    }

    override fun toString():String {
        return seats
            .map { it.toString() }
            .chunked(columns)
            .map { it.joinToString("")}
            .joinToString("\n")
    }
}

class DayEleven2020(val input: String) {

    // List where the indices are coordinates and the value is the seat status
    private val initial = SeatGrid.parse(input)

    fun getInitial(): SeatGrid {
        return initial
    }

    // Simulate your seating area by applying the seating rules repeatedly until no seats change state. How many seats end up occupied?
    fun partOne(): Int {
        var current = initial
        while(true) {
            val next = current.tick()
            if(next == current) { break }
            current = next
        }
        return current.countOccupied()
    }

    fun partTwo(): Int {
        var current = initial
        while(true) {
            val next = current.tock()
            if(next == current) { break }
            current = next
        }
        return current.countOccupied()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day11_2020.txt")
    val solver = DayEleven2020(input)
    println(solver.partOne()) //2277
    println(solver.partTwo()) //2066
}
