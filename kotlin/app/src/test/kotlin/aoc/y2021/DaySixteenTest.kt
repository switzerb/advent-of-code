package aoc.y2021

import aoc.lib.Resources.fileAsString
import org.junit.Test
import kotlin.test.assertEquals

class DaySixteenTest {

    private val literalHex = "D2FE28"
    private val opHex1 = "38006F45291200"
    private val opHex2 = "EE00D40C823060"
    private val hex1 = "8A004A801A8002F478"
    private val hex2 = "620080001611562C8802118E34"
    private val hex3 = "C0015000016115A2E0802F182340"
    private val hex4 = "A0016C880162017C3686B18A3D4780"

    @Test
    fun testToBinary() {
        assertEquals("110100101111111000101000", DaySixteen.toBinary(literalHex))
    }

    @Test
    fun testProcessPacketLiteralOnly() {
        val solver = DaySixteen(literalHex)
        val packet = solver.processPacket()
        assertEquals(6, packet.version)
        assertEquals(4, packet.type)
        assertEquals(2021, packet.value)
        assertEquals(21, packet.totalBits)
    }

    @Test
    fun testProcessPacketOperator1() {
        val solver = DaySixteen(opHex1)
        val root = solver.processPacket()
        assertEquals(1, root.version)
        assertEquals(6, root.type)
        assertEquals(2, root.subPackets.size)
        assertEquals(10, root.subPackets[0].value)
        assertEquals(11, root.subPackets[0].totalBits)
        assertEquals(20, root.subPackets[1].value)
        assertEquals(16, root.subPackets[1].totalBits)
    }

    @Test
    fun testProcessPacketOperator2() {
        val solver = DaySixteen(opHex2)
        val root = solver.processPacket()
        assertEquals(7, root.version)
        assertEquals(3, root.type)
        assertEquals(3, root.subPackets.size)
        assertEquals(1, root.subPackets[0].value)
        assertEquals(2, root.subPackets[1].value)
        assertEquals(3, root.subPackets[2].value)
    }

    @Test
    fun testP1Ex1() {
        val solver = DaySixteen(hex1)
        assertEquals(16, solver.partOne())
    }

    @Test
    fun testP1Ex2() {
        val solver = DaySixteen(hex2)
        assertEquals(12, solver.partOne())
    }

    @Test
    fun testP1Ex3() {
        val solver = DaySixteen(hex3)
        assertEquals(23, solver.partOne())
    }

    @Test
    fun testP1Ex4() {
        val solver = DaySixteen(hex4)
        assertEquals(31, solver.partOne())
    }

    @Test
    fun testP1Actual() {
        val solver = DaySixteen(fileAsString("day16_2021.txt"))
        assertEquals(886, solver.partOne())
    }

    @Test
    fun testP2SumOperation() {
        val solver = DaySixteen("C200B40A82")
        assertEquals(3, solver.partTwo())
    }

    @Test
    fun testP2MultOperation() {
        val solver = DaySixteen("04005AC33890")
        assertEquals(54, solver.partTwo())
    }

    @Test
    fun testP2MinOperation() {
        val solver = DaySixteen("880086C3E88112")
        assertEquals(7, solver.partTwo())
    }

    @Test
    fun testP2MaxOperation() {
        val solver = DaySixteen("CE00C43D881120")
        assertEquals(9, solver.partTwo())
    }

    @Test
    fun testP2LessThanOperation() {
        val solver = DaySixteen("D8005AC2A8F0")
        assertEquals(1, solver.partTwo())
    }

    @Test
    fun testP2Operation() {
        val solver = DaySixteen("F600BC2D8F")
        assertEquals(0, solver.partTwo())
    }

    @Test
    fun testP2Operation2() {
        val solver = DaySixteen(fileAsString("day16_2021.txt"))
        assertEquals(184487454837, solver.partTwo())
    }

    @Test
    fun testP2Operation3() {
        val solver = DaySixteen("9C0141080250320F1802104A08")
        assertEquals(1, solver.partTwo())
    }

    @Test
    fun testP2Actual() {
        val solver = DaySixteen("9C0141080250320F1802104A08")
        assertEquals(1, solver.partTwo())
    }
}
