package aoc.y2020

import aoc.lib.Resources.fileAsList

class Day14y2020 (val input: List<String>) {

    private val memory: MutableMap<Long, Long> = mutableMapOf()
    private val base = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    fun partOne(): Long {
        var mask = base
        input.forEach { instruction ->
            if (instruction.startsWith("mask")) {
                mask = instruction.substringAfter("= ")
            } else {
                val address = instruction.substringAfter("[").substringBefore("]").toLong()
                val value = instruction.substringAfter("= ")
                memory[address] = masked(toBinary(value), mask)
            }
        }
        return memory.values.sum()
    }

    fun partTwo(): Long {
        var mask = base
        input.forEach { instruction ->
            if (instruction.startsWith("mask")) {
                mask = instruction.substringAfter("= ")
            } else {
                val decimalAddress = instruction.substringAfter("[").substringBefore("]")
                val value = instruction.substringAfter("= ").toLong()
                decodeMemoryAddress(toBinary(decimalAddress), mask).forEach { address ->
                    memory[address] = value
                }
            }
        }
        return memory.values.sum()
    }

    fun toBinary(value: String): String {
        return value.toLong().toString(2).padStart(36,'0')
    }

    private fun masked(value: String, mask: String): Long =
        value
            .zip(mask)
            .map { (valueChar, maskChar) ->
                maskChar.takeUnless { it == 'X' } ?: valueChar
            }
            .joinToString("")
            .toLong(2)

    fun floatAddress(addr: CharArray, mask: String): String {
        mask.forEachIndexed { i,bit ->
            when(bit) {
                '1' -> addr[i] = '1'
                'X'-> addr[i] = 'X'
            }
        }
        return addr.joinToString("")
    }

    fun expand(addresses: List<CharArray>, i: Int): List<CharArray> {
        return addresses.map {
            it[i] = '1'
            it.copyOf().apply { this[i] = '0' }
        }
    }

    fun decodeMemoryAddress(memoryAddress: String, mask: String): List<Long> {

        val addresses = mutableListOf(memoryAddress.toCharArray())
        val floated = floatAddress(memoryAddress.toCharArray(), mask)

        mask.forEachIndexed { i, bit ->
            when (bit) {
                '1' -> addresses.forEach { it[i] = '1' }
                'X' -> { addresses.addAll(expand(addresses,i)) }
            }
        }
        return addresses.map { it.joinToString("").toLong(2) }
    }
}

fun main(args: Array<String>) {
    val solver = Day14y2020(fileAsList("day14_2020.txt"))
    println(solver.partOne()) //2346881602152
    println(solver.partTwo()) //6232114436321
}
