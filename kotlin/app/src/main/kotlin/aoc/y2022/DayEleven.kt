package aoc.y2022

typealias Item = Long

class Monkey(
    val items: MutableList<Item>,
    val divider: Long,
    val worryOperation: (worry: Long) -> Long,
    val monkeyTId: Int,
    val monkeyFId: Int
) {
    private var itemCounter = 0L

    fun getInspectedCount() = itemCounter

    // while there is anything in the list, throw until holding nothing
    fun takeTurn(
        withRelief: Boolean,
        mod: Long?
    ): List<Pair<Int, Item>> {
        val monkeys = buildList {
            for (item in items) {
                add(throwTo(item, withRelief, mod))
            }
        }
        items.clear()
        return monkeys
    }

    fun throwTo(
        item: Item,
        withRelief: Boolean,
        mod: Long?
    ): Pair<Int, Item> {
        itemCounter++

        var newWorries = if (mod == null) worryOperation(item) else worryOperation(item % mod)

        if (withRelief) {
            newWorries = newWorries.floorDiv(3)
        }
        if (newWorries % this.divider == 0L) {
            return Pair(monkeyTId, newWorries)
        } else {
            return Pair(monkeyFId, newWorries)
        }
    }
}

class DayEleven(val monkeys: List<Monkey>) {

//    val monkeys = buildList {
//    }

    fun round(
        withRelief: Boolean = true,
        mod: Long? = null
    ) {
        for (id in monkeys.indices) {
            val throwsTo = monkeys[id].takeTurn(withRelief, mod)
            for (next in throwsTo) {
                monkeys[next.first].items.add(next.second)
            }
        }
    }

    fun getMonkeyBusiness(): Long = monkeys
        .map(Monkey::getInspectedCount)
        .sortedDescending()
        .take(2)
        .reduce { a, item -> a * item }

    fun partOne(rounds: Int = 20): Long {
        repeat(rounds) {
            round()
        }
        return getMonkeyBusiness()
    }

    fun partTwo(rounds: Int = 10000): Long {
        val mod = monkeys.map {
            it.divider
        }.reduce { a, n -> a * n }

        for (i in 1..rounds) {
            round(withRelief = false, mod = mod)
        }
        return getMonkeyBusiness()
    }
}

fun main(args: Array<String>) {
    val monkeys = listOf(
        Monkey(
            items = mutableListOf(91, 66),
            worryOperation = { w -> w * 13 },
            divider = 19,
            monkeyTId = 6,
            monkeyFId = 2
        ),
        Monkey(
            items = mutableListOf(78, 97, 59),
            worryOperation = { w -> w + 7 },
            divider = 5,
            monkeyTId = 0,
            monkeyFId = 3
        ),
        Monkey(
            items = mutableListOf(57, 59, 97, 84, 72, 83, 56, 76),
            worryOperation = { w -> w + 6 },
            divider = 11,
            monkeyTId = 5,
            monkeyFId = 7
        ),
        Monkey(
            items = mutableListOf(81, 78, 70, 58, 84),
            worryOperation = { w -> w + 5 },
            divider = 17,
            monkeyTId = 6,
            monkeyFId = 0
        ),
        Monkey(
            items = mutableListOf(60),
            worryOperation = { w -> w + 8 },
            divider = 7,
            monkeyTId = 1,
            monkeyFId = 3
        ),
        Monkey(
            items = mutableListOf(57, 69, 63, 75, 62, 77, 72),
            worryOperation = { w -> w * 5 },
            divider = 13,
            monkeyTId = 7,
            monkeyFId = 4
        ),
        Monkey(
            items = mutableListOf(73, 66, 86, 79, 98, 87),
            worryOperation = { w -> w * w },
            divider = 3,
            monkeyTId = 5,
            monkeyFId = 2
        ),
        Monkey(
            items = mutableListOf(95, 89, 63, 67),
            worryOperation = { w -> w + 2 },
            divider = 2,
            monkeyTId = 1,
            monkeyFId = 4
        )
    )
//    val input = fileAsString("2022/day11_2022.txt")
    val solver = DayEleven(monkeys)
//    println(solver.partOne())
    println(solver.partTwo())
}
