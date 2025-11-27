package aoc.y2020

import kotlin.system.measureTimeMillis

class Day152020(val input: List<Int>) {

    private val start = input.toMutableList()
    private val said = mutableMapOf<Int, Int>()
    private var previous: Int = -1;

    fun say(number: Int, turn: Int): Unit { said[number] = turn }

    fun play(target: Int) : Int {
        (1..target).map { turn ->
            if(start.isNotEmpty()){
                previous = start[0]
                start.removeAt(0)
                if(start.size >= 1) {
                    say(previous, turn)
                }
            } else {
                val lastSaid = said[previous]
                say(previous,turn - 1)
                previous = if(lastSaid == null) 0 else (turn - 1) - lastSaid
            }
        }
        return previous

    }
    fun partOne(): Int {
        return play(2020)
    }

    fun partTwo(): Int {
        return play(30000000)
    }

}

fun main(args: Array<String>) {
  val solver = Day152020(listOf(2,1,10,11,0,6))
    println(solver.partOne()) //232
    val timeInMillis = measureTimeMillis {
        println(solver.partTwo())
    }
    println(timeInMillis)

}

// 203ms    10,000
// 771ms    100,000
// 1525.22s 1,000,000
