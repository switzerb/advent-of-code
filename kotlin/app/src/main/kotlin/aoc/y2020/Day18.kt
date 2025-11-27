package aoc.y2020

import aoc.lib.MutableStack
import aoc.lib.Resources.fileAsList

class Day18(val input: List<String>) {

    private val expressions = input.map { it.replace(" ", "") }

    private fun eval(exp: CharIterator) : Long {
        val nums = mutableListOf<Long>()
        var op = '_'
        while(exp.hasNext()) {
            when(val next = exp.nextChar()) {
                '+' -> op = next
                '*' -> op = next
                '(' -> nums.add(eval(exp))
                ')' -> break
                else -> {
                    if(!next.isDigit()) throw IllegalArgumentException("$next cannot be evaluated")
                    nums += next.toString().toLong()
                }
            }
            if(nums.size == 2) {
                val (a,b) = nums
                with(nums) {
                    clear()
                    plusAssign(if (op == '+') a + b else a * b)
                }
            }
        }
        return nums.first()
    }

    private fun evalWithPrecedence(exp: CharIterator) : Long {
        val products = mutableListOf<Long>()
        var adds: Long = 0
        while (exp.hasNext()) {
            when(val next = exp.nextChar()) {
                '*' -> {
                    products += adds
                    adds = 0
                }
                '(' -> adds += evalWithPrecedence(exp)
                '+' -> continue
                ')' -> break
                else -> {
                    if(!next.isDigit()) throw IllegalArgumentException("$next cannot be evaluated")
                    adds += next.toString().toLong()
                }
            }
        }
        return (products + adds).reduce { a, it -> a * it }
    }

    fun partOne(): Long {
        return expressions.sumOf { exp -> eval(exp.iterator()) }
    }

    fun partTwo() : Long {
        return expressions.sumOf { exp -> evalWithPrecedence(exp.iterator()) }
    }
}

private fun precedence(c: Char) : Int {
    return when(c) {
        ')' -> 4
        '*' -> 3
        '/' -> 3
        '+' -> 2
        '-' -> 2
        '(' -> 1
        else -> 0
    }
}

private fun result(op: Char, a: Long, b: Long): Long {
    return when(op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> a / b
        else -> throw IllegalArgumentException("$op is not a recognized operator.")
    }
}

fun evaluate(expr: CharIterator) : Long {
    val ops = MutableStack<Char>();
    val values = MutableStack<Long>();

    while (expr.hasNext()) {
        val c = expr.nextChar()
        when(c) {
            '(' -> ops.push(c)
            ')' -> {
                while(!ops.isEmpty() && ops.peek() != '(' ) {
                    values.push(result(ops.pop(), values.pop(), values.pop()))
                    ops.pop()
                }
            }
            '+', '-', '*', '/' -> {
                while(!ops.isEmpty() && (precedence(ops.peek()!!) >= precedence(c))) {
                    values.push(result(ops.pop(), values.pop(), values.pop()))
                }
                ops.push(c)
            }
            else -> values.push(c.toString().toLong())
        }
    }
    return values.pop()
}


fun main(args :Array<String>) {
    val solver = Day18(fileAsList("day18_2020.txt"))
    println(solver.partOne()) //11076907812171
    println(solver.partTwo()) //283729053022731

val expression = "(1+(1+2))"
    println(evaluate(expression.iterator()))
}
