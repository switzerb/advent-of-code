package aoc.y2020

import aoc.lib.Resources.fileAsString
import kotlin.math.sqrt

data class Tile(val id: Int, val s: String) {

    private var pixels: CharArray = s
        .filter { it == '.' || it == '#' }
        .toCharArray()
    private val width: Int = sqrt(pixels.size.toDouble()).toInt()

    private val borders: List<String> = Orientation.values().map { getBorder(it) }
    private val flipped: List<String> = borders.map { it.reversed() }
    val edges = borders + flipped

    init {
        assert(pixels.size == width * width) { "Incorrect size of string input for image." }
    }

    fun getBorder(dir: Orientation): String =
        when (dir) {
            Orientation.Top -> pixels.take(width).joinToString("")
            Orientation.Bottom -> pixels.takeLast(width).joinToString("")
            Orientation.Left -> pixels.filterIndexed { idx,_ -> idx % width == 0 }.joinToString("")
            Orientation.Right -> pixels.filterIndexed { idx,_ -> (idx % width) == 9 }.joinToString("")
        }

    fun hasMatchedEdge(other: Tile) : Boolean = edges.count { edge -> other.hasMatch(edge) } > 0

    fun hasMatch(edge: String): Boolean = edge in edges

    fun flip() : Tile {
        pixels = pixels
            .toList()
            .chunked(width)
            .map { it.reversed() }
            .flatten()
            .toCharArray()
        return this
    }

    fun rotateClockwise() : Tile {
        val chunker = pixels
            .toList()
            .chunked(width)

        pixels = chunker
            .mapIndexed { x, row ->
                row.mapIndexed { y, _ ->
                    chunker[y][x]
                }.reversed()
            }
            .flatten()
            .toCharArray()
        return this
    }

    override fun toString(): String {
        return pixels
            .toList()
            .chunked(width)
            .joinToString("\n") {
                it.joinToString("")
            }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

enum class Orientation {
    Top, Right, Bottom, Left
}

class Day20(val input: String) {

    private val tiles: List<Tile> = parse()

    fun parse(): List<Tile> {
        return input
            .split("\n\n")
            .map { each ->
                val (identity, pixels) = each.split(":")
                val id = identity.substringAfter(" ").substringBefore(":").toInt()
                Tile(id, pixels.trim())
            }
    }

    fun countTotalEdges() : Int = tiles.sumOf { tile -> tile.edges.size }

    // TODO: Blog post for associate family of methods
    private fun buildSharedEdges(): Map<Tile,List<Tile>> {
        return tiles.associateWith { tile ->
            tiles
                .filterNot { tile.id == it.id }
                .filter { other -> tile.hasMatchedEdge(other) }
        }
    }

//    corner == 2 sides in common
//    edge == 3 sides in common
//    inner = 4 sides in common
    fun partOne(): Long {
        return buildSharedEdges()
            .filterValues { it.size == 2 }
            .keys.map { it.id }
            .fold(1L, { acc, item -> item * acc })
    }

/*
    organize the tiles into their correct spaces
       ~~ build histogram of tiles to the set of shared edges -- an adjacency graph of the larger image
       ~~ flip method
       ~~ rotate method
       start with an edge piece
         flip or rotate until that corner is the upper left of the image (e.g. the no-match is the top and left edge)
         look at the right edge and find the matching tile
             rotate and flip until that one is in the correct orientation
                  rotate through each direction,
                    then and rotate through again until you get a match to the edge
             keep going
    stitch the final tiles into one image
        remove all the matched borders
        concatenate them all into one string
    pattern matching to find all the "sea monsters"
    return the habitat's water roughness
        total number of hashes in final image - (num sea monsters found * 15 (hash in monster))
*/
    fun partTwo(): Int {
        return 0
    }

}

fun main(args: Array<String>) {
    val input = fileAsString("day20_2020.txt")
    val solver = Day20(input)
    println(solver.partOne())

}
