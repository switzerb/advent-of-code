package aoc.y2022

import aoc.lib.Resources.fileAsString

class DayTwentyOne(private val input: String) {

    sealed interface Monkey {
        val name: String
    }

    data class MathMonkey(
        override val name: String,
        val number: Long
    ) : Monkey

    val monkeys: Map<String, Monkey> = input
        .split("\n")
        .map {
            val name = it.substringBefore(":")
            val rest = it.substringAfter(":").trim()
            when {
                rest.isNumeric() -> MathMonkey(name = name, number = rest.toLong())
                else -> {
                    val (left, op, right) = rest.split(" ")
                    TeamMonkey(
                        name,
                        left,
                        right,
                        op
                    )
                }
            }
        }.associateBy { it.name }

    val toHuman = follow("root")

    fun String.isNumeric(): Boolean = this.all { char -> char.isDigit() }

    fun yell(name: String): Long {
        val monkey: Monkey = monkeys[name]!!
        return when (monkey) {
            is MathMonkey -> monkey.number
            is TeamMonkey -> {
                monkey.operate(yell(monkey.left), yell(monkey.right))
            }
        }
    }

    fun follow(name: String): Set<Monkey> {
        val monkey = monkeys[name]
        return when (monkey) {
            is MathMonkey -> {
                if (monkey.name == "humn") {
                    return setOf(monkey)
                } else {
                    setOf()
                }
            }
            is TeamMonkey -> {
                val left = follow(monkey.left)
                val right = follow(monkey.right)
                if (left.isNotEmpty()) {
                    left + monkey
                } else if (right.isNotEmpty()) {
                    right + monkey
                } else {
                    setOf()
                }
            }
            else -> throw Error("I have no idea what I am doing")
        }
    }

    fun compute(name: String, other: Long): Long {
        val monkey = monkeys[name]
        return when (monkey) {
            is MathMonkey -> {
                if (name == "humn") other else monkey.number
            }
            is TeamMonkey -> {
                if (name == "root") {
                    if (monkeys[monkey.left] in toHuman) {
                        compute(monkey.left, yell(monkey.right))
                    } else {
                        compute(monkey.right, yell(monkey.left))
                    }
                } else if (monkeys[monkey.left] in toHuman) {
                    compute(monkey.left, monkey.solveForLeft(other, yell(monkey.right)))
                } else {
                    compute(monkey.right, monkey.solveForRight(other, yell(monkey.left)))
                }
            }
            else -> throw Error("I have no idea what I am doing.")
        }
    }

    fun partOne(): Long = yell("root")

    //    This causes root to get the same number, 150, from both of its monkeys.
    fun partTwo(): Long {
        return compute("root", 0L)
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day21_2022.txt")
    val solver = DayTwentyOne(input)
    println(solver.partOne())
    println(solver.partTwo())
}
