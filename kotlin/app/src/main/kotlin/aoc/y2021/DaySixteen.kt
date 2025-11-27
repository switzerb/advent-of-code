package aoc.y2021

import aoc.lib.Resources.fileAsString

data class Packet(
    val version: Int,
    val type: Int,
    val totalBits: Int,
    val value: Long? = null,
    val subPackets: List<Packet> = emptyList()
) {
    fun totalVersions(): Int = version + subPackets.sumOf(Packet::totalVersions)

    fun getTotalValue(): Long {
        return when (type) {
            0 -> subPackets.sumOf(Packet::getTotalValue)
            1 -> subPackets.map(Packet::getTotalValue).reduce(Long::times)
            2 -> subPackets.minOf(Packet::getTotalValue)
            3 -> subPackets.maxOf(Packet::getTotalValue)
            4 -> value!!
            5 -> if (subPackets.first().getTotalValue() > subPackets.last().getTotalValue()) 1 else 0
            6 -> if (subPackets.first().getTotalValue() < subPackets.last().getTotalValue()) 1 else 0
            7 -> if (subPackets.first().getTotalValue() == subPackets.last().getTotalValue()) 1 else 0
            else -> throw Error("Operation type $type is unknown")
        }
    }
}

class DaySixteen(private val input: String) {

    companion object {
        fun toBinary(hex: String): String {
            return hex.toCharArray().map {
                binaryLookup[it]
            }.joinToString("")
        }

        private val binaryLookup: Map<Char, String> = mapOf(
            '0' to "0000",
            '1' to "0001",
            '2' to "0010",
            '3' to "0011",
            '4' to "0100",
            '5' to "0101",
            '6' to "0110",
            '7' to "0111",
            '8' to "1000",
            '9' to "1001",
            'A' to "1010",
            'B' to "1011",
            'C' to "1100",
            'D' to "1101",
            'E' to "1110",
            'F' to "1111",
        )
    }

    private var idx = 0
    private val message = toBinary(input)

    private fun read(n: Int = 1): Int {
        val temp = idx + n
        idx += n
        return temp
    }

    private fun String.getHeaders(): Pair<Int, Int> {
        val version = substring(idx, read(3)).toInt(2)
        val type = substring(idx, read(3)).toInt(2)
        return Pair(version, type)
    }

    private fun getLiteralValue(): Long {
        var value = ""
        do {
            val prefix = message.substring(idx, read(1)).toInt()
            value += message.substring(idx, read(4))
        } while (prefix != 0)
        return value.toLong(2)
    }

    private fun getSubPacketLen() = message.substring(idx, read(15)).toInt(2)
    private fun getSubPacketCount() = message.substring(idx, read(11)).toInt(2)
    private fun getLengthId(): Int = message.substring(idx, read(1)).toInt()

    fun processPacket(): Packet {
        val startBitCount = idx
        val (version, type) = message.getHeaders()

        // literal value
        if (type == 4) {
            val literal = getLiteralValue()
            return Packet(
                version = version,
                type = type,
                totalBits = idx - startBitCount,
                value = literal,
            )
            // operator with subPackets
        } else {
            val lengthId = getLengthId()
            val subPacketLen = when (lengthId) {
                0 -> getSubPacketLen()
                1 -> getSubPacketCount()
                else -> throw Error("LengthId $lengthId is unrecognized")
            }
            var count = 0
            val subPackets = mutableListOf<Packet>()

            while (subPacketLen > count) {
                val packet = processPacket()
                count += when (lengthId) {
                    0 -> packet.totalBits
                    1 -> 1
                    else -> throw Error("LengthId $lengthId is unrecognized")
                }
                subPackets.add(packet)
            }

            return Packet(
                version = version,
                type = type,
                totalBits = idx - startBitCount,
                subPackets = subPackets
            )
        }
    }

    fun partOne(): Int {
        idx = 0
        return processPacket().totalVersions()
    }

    fun partTwo(): Long {
        idx = 0
        return processPacket().getTotalValue()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day16_2021.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
    println(solver.partTwo())
}
