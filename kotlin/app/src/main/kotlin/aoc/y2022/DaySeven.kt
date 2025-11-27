package aoc.y2022

import aoc.lib.Resources.fileAsList

class DaySeven(private val input: List<String>) {

    val filesMap = mutableMapOf<String, Int>()

    //    builds a map of directory to total file size of containing files
    fun buildMap() {
        filesMap[""] = 0
        var current = ""
        for (item in input) {
            /*
              we want to match on either a directory change
              or a file size. everything else is ignored
             */
            val match = """[$] cd (.*)|(\d+).*"""
                .toRegex()
                .matchEntire(item) ?: continue
            match.groups[1]?.value?.let { dir ->
                current = when (dir) {
                    "/" -> ""
                    ".." -> current.substringBeforeLast('/', "")
                    else -> if (current.isEmpty()) dir else "$current/$dir"
                }
            } ?: match.groups[2]?.value?.toIntOrNull()?.let { size ->
                var dir = current
                while (true) {
                    filesMap.put(dir, filesMap.getOrElse(dir) { 0 } + size)
                    if (dir.isEmpty()) break
                    dir = dir.substringBeforeLast('/', "")
                }
            }
        }
    }

    fun partOne(): Int {
        buildMap()
        return filesMap
            .values
            .filter { it <= 100000 }
            .sum()
    }

    fun partTwo(): Int? {
        buildMap()
        val total = filesMap.getValue("")
        return filesMap
            .values
            .filter { 70000000 - (total - it) >= 30000000 }
            .minOrNull()
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day07_2022.txt")
    val solver = DaySeven(input)
    println(solver.partOne())
    println(solver.partTwo())
}
