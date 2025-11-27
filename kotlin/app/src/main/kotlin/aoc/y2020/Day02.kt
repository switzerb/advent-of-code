package aoc.y2020

class Password(
        private val min: Int,
        private val max: Int,
        private val letter: Char,
        private val text: String
        ) {

    fun isSledValid(): Boolean {
        val letters : String = text.filter { it == letter }
        return letters.length in min..max;
    }

    fun isTobogganValid(): Boolean {
        val pos1 = text[min - 1]
        val pos2 = text[max - 1]
        return (pos1 == letter && pos2 != letter) || (pos1 != letter && pos2 == letter)
    }
}

class DayTwo2020(private val input: String) {

    private val passwords = mutableListOf<Password>()

    init { parser() }

    private fun parser(): Unit {
        val lines = input.trim().lines()
        lines.forEach {
            val parts = it.split(" ")
            val min = parts[0].substringBefore("-").toInt()
            val max = parts[0].substringAfter("-").toInt()
            val letter = parts[1].first()
            val text = parts[2]
            passwords.add(Password(min,max,letter,text))
        }
    }

    fun partOne(): Int {
        val valid = passwords.filter { it.isSledValid() }
        return valid.size
    }

    fun partTwo(): Int {
        val valid = passwords.filter { it.isTobogganValid() }
        return valid.size
    }
}

fun main(args: Array<String>) {
    val cl = DayTwo2020::class.java.classLoader.getResource("day2_2020.txt") ?: return
    val input = cl.readText();
    val solver = DayTwo2020(input)
    println(solver.partOne()) // 524
    println(solver.partTwo()) // 485
}
