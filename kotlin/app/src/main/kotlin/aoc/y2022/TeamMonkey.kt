package aoc.y2022

data class TeamMonkey(
    override val name: String = "",
    val left: String = "",
    val right: String = "",
    val op: String = "+"
) : DayTwentyOne.Monkey {

    fun operate(left: Long, right: Long): Long =
        when (op) {
            "+" -> left + right
            "-" -> left - right
            "*" -> left * right
            "/" -> left / right
            else -> throw UnsupportedOperationException("Monkey don't play that.")
        }

    fun solveForLeft(right: Long, answer: Long): Long =
        when (op) {
            "+" -> right - answer
            "-" -> right + answer
            "*" -> right / answer
            "/" -> right * answer
            else -> throw UnsupportedOperationException("Monkey don't play that.")
        }

    fun solveForRight(answer: Long, left: Long): Long =
        when (op) {
            "+" -> answer - left
            "-" -> left - answer
            "*" -> answer / left
            "/" -> left / answer
            else -> throw UnsupportedOperationException("Monkey don't play that.")
        }
}
