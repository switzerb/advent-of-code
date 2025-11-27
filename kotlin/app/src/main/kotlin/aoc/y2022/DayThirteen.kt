package aoc.y2022

import aoc.lib.Resources.fileAsList

sealed class Packet : Comparable<Packet> {
    companion object {
        fun fromString(input: String): Packet {
            val chars = input.toCharArray().iterator()
            return from(chars)
        }

        fun from(chars: Iterator<Char>): Packet {
            val packets = mutableListOf<Packet>()
            var digits: Int? = null
            while (chars.hasNext()) {
                when (val item = chars.next()) {
                    ',' -> {
                        if (digits != null) packets.add(IntPacket(digits))
                        digits = null
                    }
                    ']' -> {
                        if (digits != null) packets.add(IntPacket(digits))
                        return ListPacket(packets)
                    }
                    '[' -> packets.add(from(chars))
                    else -> {
                        if (digits == null) {
                            digits = item.digitToInt()
                        } else {
                            digits = digits * 10 + item.digitToInt()
                        }
                    }
                }
            }
            return ListPacket(packets)
        }
    }
}

data class IntPacket(val value: Int) : Packet() {
    override fun compareTo(other: Packet): Int = when (other) {
        is IntPacket -> value.compareTo(other.value)
        is ListPacket -> ListPacket(listOf(this)).compareTo(other)
    }
}

data class ListPacket(val children: List<Packet>) : Packet() {
    override fun compareTo(other: Packet): Int = when (other) {
        is IntPacket -> compareTo(ListPacket(listOf(other)))
        is ListPacket ->
            children
                .zip(other.children)
                .map { it.first.compareTo(it.second) }
                .firstOrNull { it != 0 } ?: children.size.compareTo(other.children.size)
    }
}

/**
 * --- Day 13: Distress Signal ---
 * https://adventofcode.com/2022/day/13
 */
class DayThirteen(val input: List<String>) {

    private val packets = input
        .filter { it.isNotBlank() }
        .map {
            Packet.fromString(it)
        }

    fun inOrder(left: Packet, right: Packet): Boolean = left < right

    // how many pairs of packets are in the right order?
    fun partOne(): Int {
        return packets.chunked(2).map {
            Pair(it.first(), it.last())
        }.mapIndexed { idx, pair ->
            if (inOrder(pair.first, pair.second)) {
                idx + 1
            } else 0
        }.sum()
    }

    fun partTwo(): Int {
        val marker1 = Packet.fromString("[[2]]")
        val marker2 = Packet.fromString("[[6]]")

        val withMarkers = packets.toMutableList()
        withMarkers.add(marker1)
        withMarkers.add(marker2)
        withMarkers.sort()
        return withMarkers.mapIndexed { idx, packet ->
            if (packet === marker1 || packet === marker2) {
                idx + 1
            } else 1
        }.reduce { a, n -> a * n }
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day13_2022.txt")
    val solver = DayThirteen(input)
    println(solver.partOne()) // 12324, 11714
    println(solver.partTwo())
}
