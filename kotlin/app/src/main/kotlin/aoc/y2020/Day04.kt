package aoc.y2020

data class Passport(val attributes: Map<String,String>) {

    private val required = listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")

    //    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    fun isValidByr(v:String) : Boolean {
        return v.length == 4 && v.toInt() in 1920..2002
    }

    //    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    fun isValidIyr(v:String):Boolean {
        return v.length == 4 && v.toInt() in 2010..2020
    }

    //    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    fun isValidEyr(v:String):Boolean {
        return v.length == 4 && v.toInt() in 2020..2030
    }

    //    hgt (Height) - a number followed by either cm or in:
    //      If cm, the number must be at least 150 and at most 193.
    //      If in, the number must be at least 59 and at most 76.
    fun isValidHgt(v: String) : Boolean {
        val len = v.length - 1
        val unit = v.substring(len-1..len)
        val num = v.substring(0 until len-1)
        return when (unit) {
            "cm" -> { num.toInt() in 150..193 }
            "in" -> { num.toInt() in 59..76 }
            else -> { false }
        }
    }

    //    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    fun isValidHcl(v:String):Boolean {
        return v.matches("#[a-f0-9]{6}".toRegex())
    }

    //    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    fun isValidEcl(v:String):Boolean {
        return v.matches("amb|blu|brn|gry|grn|hzl|oth".toRegex())
    }

    //    pid (Passport ID) - a nine-digit number, including leading zeroes.
    fun isValidPid(v:String):Boolean {
        return v.matches("^[0-9]{9}$".toRegex())
    }
//    cid (Country ID) - ignored, missing or not.


    fun isValid() : Boolean {
        if (!isComplete()) return false
        val valid = attributes.entries.map { (k,v) ->
            when(k) {
                "byr" -> isValidByr(v)
                "iyr" -> isValidIyr(v)
                "eyr" -> isValidEyr(v)
                "hgt" -> isValidHgt(v)
                "hcl" -> isValidHcl(v)
                "ecl" -> isValidEcl(v)
                "pid" -> isValidPid(v)
                "cid" -> true
                else -> false
            }
        }
        return valid.all { it }
    }

    fun isComplete():Boolean {
        val valid = required.map { att ->
            attributes.containsKey(att)
        }
        return valid.all { it }
    }
}

class DayFour2020(val input: String) {

    private val passports = parser()

    private fun builder(fields: List<String>): Passport {
        var valuesMap = mutableMapOf<String,String>()
        fields.map {
            val pair = it.split(":")
            valuesMap.put(pair[0],pair[1])
        }
        return Passport(valuesMap)
    }

    fun parser(): List<Passport> {
        val split = input.trim().split("\n\n".toRegex())
        return split.map { item ->
            val fields = item.split("\\s|\n".toRegex())
            builder(fields)
        }
    }

    fun partOne():Int {
        var count = 0
        passports.forEach {
            if(it.isComplete()){
                count++
            }
        }
        return count
    }

    fun partTwo():Int {
        var count = 0
        passports.forEach {
            if(it.isValid()) {
                count++
            }
        }
        return count
    }
}

fun main(args: Array<String>) {
    val cl = DayFour2020::class.java.classLoader.getResource("day4_2020.txt") ?: return
    val input = cl.readText()
    val solver = DayFour2020(input)
    println(solver.partOne()) //210
    println(solver.partTwo()) //131
}
