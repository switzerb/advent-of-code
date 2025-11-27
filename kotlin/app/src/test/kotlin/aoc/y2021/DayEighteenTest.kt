package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayEighteenTest {

    val noop = DayEighteen(listOf("noop"))
    private fun String.toSnailFishNumber() = split("")

    @Test
    fun testParse() {
        val first = "[1,2]".toSnailFishNumber()
        val second = "[[3,4],5]".toSnailFishNumber()
        val result = "[[1,2],[[3,4],5]]".toSnailFishNumber()
        assertEquals(result, noop.add(first, second))
    }

    @Test
    fun testExplode1() {
        val number = "[[[[[9,8],1],2],3],4]".toSnailFishNumber()
        val result = "[[[[0,9],2],3],4]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode2() {
        val number = "[7,[6,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[7,[6,[5,[7,0]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode3() {
        val number = "[[6,[5,[4,[3,2]]]],1]".toSnailFishNumber()
        val result = "[[6,[5,[7,0]]],3]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode4() {
        val number = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode5() {
        val number = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testSplit1() {
        val number = "[[[[0,7],4],[".split("") + listOf("15", ",", "[", "0", ",", "13") + "]]],[1,1]]".split("")
        val result = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"
        assertEquals(result, noop.split(number).joinToString(""))
    }

    @Test
    fun testSplit2() {
        val number = "[[[[0,7],4],[[7,8],[0,".split("") + listOf("13") + "]]],[1,1]]".split("")
        val result = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]"
        assertEquals(result, noop.split(number).joinToString(""))
    }

    @Test
    fun testReduce() {
        val solver = DayEighteen(listOf("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]"))
        val result = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        assertEquals(
            result,
            solver.reduce(
                first = "[[[[4,3],4],4],[7,[[8,4],9]]]".toSnailFishNumber(),
                second = "[1,1]".toSnailFishNumber()
            ).joinToString("")
        )
    }

    @Test
    fun testSum1() {
        val input = listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]")
        val solver = DayEighteen(input)
        val result = "[[[[1,1],[2,2]],[3,3]],[4,4]]"
        assertEquals(result, solver.sum().joinToString(""))
    }

    @Test
    fun testSum2() {
        val input = listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]")
        val solver = DayEighteen(input)
        val result = "[[[[3,0],[5,3]],[4,4]],[5,5]]"
        assertEquals(result, solver.sum().joinToString(""))
    }

    @Test
    fun testSum3() {
        val input = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]",
        )
        val solver = DayEighteen(input)
        val result = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
        assertEquals(result, solver.sum().joinToString(""))
    }

    @Test
    fun testMagnitude() {
        assertEquals(29, noop.getMagnitude("[9,1]".toSnailFishNumber()))
        assertEquals(21, noop.getMagnitude("[1,9]".toSnailFishNumber()))
        assertEquals(129, noop.getMagnitude("[[9,1],[1,9]]".toSnailFishNumber()))
        assertEquals(143, noop.getMagnitude("[[1,2],[[3,4],5]]".toSnailFishNumber()))
        assertEquals(1384, noop.getMagnitude("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]".toSnailFishNumber()))
        assertEquals(445, noop.getMagnitude("[[[[1,1],[2,2]],[3,3]],[4,4]]".toSnailFishNumber()))
        assertEquals(791, noop.getMagnitude("[[[[3,0],[5,3]],[4,4]],[5,5]]".toSnailFishNumber()))
        assertEquals(1137, noop.getMagnitude("[[[[5,0],[7,4]],[5,5]],[6,6]]".toSnailFishNumber()))
        assertEquals(
            3488,
            noop.getMagnitude("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]".toSnailFishNumber())
        )
    }

    @Test
    fun testPartOne() {
        val input = listOf(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
        )
        val solver = DayEighteen(input)
        assertEquals(4140, solver.partOne())
    }

    @Test
    fun testPartTwo() {
        val input = listOf(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
        )
        val solver = DayEighteen(input)
        assertEquals(3993, solver.partTwo())
    }
}
