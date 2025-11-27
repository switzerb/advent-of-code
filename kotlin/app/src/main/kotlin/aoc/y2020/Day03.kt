package aoc.y2020

data class Slope(val over: Int, val down: Int)

class DayThree2020(private val input: String) {

    private fun parser(): List<String> {
        return input.trim().lines()
    }

    private val paths = listOf(
            Slope(1,1),
            Slope(3,1),
            Slope(5,1),
            Slope(7,1),
            Slope(1,2)
    )

    fun countTrees(slope: Slope): Int {
        val map = parser()
        val depth = map.size - 1
        val width = map[0].length
        var over = 0
        var count = 0

        for(i in 0..depth step slope.down) {
            if(i == 0) { continue }
            over+=slope.over
            if(over >= width) { over-=width }
            if(map[i][over] == '#') {
                count++
            }
        }
        return count
    }

    fun partOne():Int {
        return countTrees(Slope(3,1))
    }

    fun partTwo():Long {
        val nums = paths.map { countTrees(it).toLong() }
        return nums.reduce { n, i -> n * i }
    }
}

fun main(args: Array<String>) {
    val cl = DayThree2020::class.java.classLoader.getResource("day3_2020.txt")
    val input = cl.readText()
    val solver = DayThree2020(input)
    println("part one: " + solver.partOne()) // 178
    println("part two: " + solver.partTwo()) // 3492520200
}
