package aoc

const val UP = '('
const val DOWN = ')'

class DayOne(val input: String) {

    fun partOne(): Int {
        val isUp = input.count { it == UP }
        return isUp - (input.length - isUp)
    }
}

fun main(args: Array<String>) {
    val cl = DayOne::class.java.classLoader.getResource("input.txt") ?: return

    val input = cl.readText();
    val solver = DayOne(input)
    println(solver.partOne())
}
