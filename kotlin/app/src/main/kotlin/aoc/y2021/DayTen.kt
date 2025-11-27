package aoc.y2021
import aoc.lib.Resources.fileAsString

// LIFO
class Stack {
    val stack: MutableList<Char> = mutableListOf()
    fun push(item: Char) = stack.add(0, item)
    fun pop(): Char = stack.removeAt(0)
    fun isEmpty(): Boolean = stack.size == 0
}

class DayTen(private val input: String) {
    val chunks = input.parse()

    val corrupted = mutableListOf<Char>()
    val incompletes = mutableListOf<List<Char>>()

    val syntax_error_score = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val autocomplete_score = mapOf(
        '(' to 1,
        '[' to 2,
        '{' to 3,
        '<' to 4,
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )
    val matches = mapOf('[' to ']', '(' to ')', '<' to '>', '{' to '}')

    fun processChunk(chunk: CharArray) {
        val stack = Stack()
        chunk.forEach { char ->
            when {
                char in matches -> stack.push(char)
                else -> {
                    val match = stack.pop()
                    if (matches[match] != char) {
                        corrupted.add(char)
                        return
                    }
                }
            }
        }
        val completion = mutableListOf<Char>()
        if (stack.isEmpty()) {
            return
        } else {
            while (!stack.isEmpty()) {
                completion.add(stack.pop())
            }
            incompletes.add(completion.toList())
        }
    }

    fun partOne(): Int {
        chunks.map { chunk ->
            processChunk(chunk)
        }
        // if the stack is empty the chunk is valid
        val counts = corrupted.groupingBy { it }.eachCount()
        var total = 0
        counts.forEach { char, count ->
            total += syntax_error_score[char]?.times(count) ?: 0
        }
        return total
    }

    fun partTwo(): Long {
        chunks.map { chunk ->
            processChunk(chunk)
        }
        val allScores = mutableListOf<Long>()

        incompletes.map { complete ->
            val score = calculateAutocompleteScore(complete)
            allScores.add(score)
        }
        allScores.sort()
        val midIdx = (allScores.size - 1) / 2
        return allScores[midIdx]
    }

    fun calculateAutocompleteScore(chars: List<Char>): Long {
        return chars.fold(0) { total, char ->
            (total * 5) + autocomplete_score[char]!!
        }
    }

    private fun String.parse() = lines().map {
        it.toCharArray()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day10_2021.txt")
    val solver = DayTen(input)
//    println(solver.partOne())
    println(solver.partTwo()) // 237311081 too low
}
