package aoc.y2020

import aoc.lib.Resources.fileAsList

class Day21(val input: List<String>) {

    private val food : Map<Set<String>, Set<String>> = parse()
    private val ingredients: Set<String> = food.keys.flatten().toSet()
    private val allergens: Set<String> = food.values.flatten().toSet()

    private fun parse(): Map<Set<String>, Set<String>> =
        input.map { item ->
            val i = item.substringBefore(" (").split(" ").toSet()
            val a = item.substringAfter("(contains ").substringBefore(")").split(", ").toSet()
            i to a
        }.toMap()

    fun nonAllergenic(): Set<String> {
        return ingredients subtract allergens.flatMap { allergen ->
            food
                .filter { allergen in it.value }
                .map { it.key }
                .reduce { acc, ingredient -> ingredient intersect acc }
        }.toSet()
    }

    fun partOne(): Int {
        val nonAllergenic = nonAllergenic()
        return food.keys.sumOf { recipe ->
            recipe.count { it in nonAllergenic }
        }
    }

    fun buildPotentials(): MutableMap<String, MutableSet<String>> {
        val nonAllergenic = nonAllergenic()
        // create a map with allergy as key and set of all ingredients that:
        //          - are in a food have that allergy in it,
        //          - minus safe ingredients
        //          - that are a member of the food that has that allergen in it

        return allergens.map { allergen ->
            allergen to food.entries
                .filter { f -> allergen in f.value }
                .map { f -> (f.key subtract nonAllergenic ) }
                .reduce { a, b -> a intersect b }
                .toMutableSet()
        }.toMap().toMutableMap()
    }

    fun partTwo(): String {
        // take the list of all potential ingredients by allergy
        // while there are still allergens in the map
//              - filter out the ones with only one ingredient as an option
//              - add it to the list of ones we know for sure
//              - remove that allergen from the list of potentials
//              - remove the ingredient from the potentials
        val potentials = buildPotentials()
        val certain: MutableMap<String,String> = mutableMapOf()

        while(potentials.isNotEmpty()) {
            val unique = potentials
                .filter { it.value.size == 1 }
                .map { it.key to it.value.first() }
                .toMap()
            certain.putAll(unique)
            unique.keys.map { key -> potentials.remove(key) }
            potentials.values.forEach { ingredients -> ingredients.removeAll(unique.values) }
        }
        return certain.entries.sortedBy { it.key }.joinToString(",") { it.value }
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("day21_2020.txt")
    val solver = Day21(input)
    println(solver.partOne())
    println(solver.partTwo())
}
