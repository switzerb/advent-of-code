package aoc.y2020

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class DayFour2020Test {
    private val input = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm
        
        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929
        
        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm
        
        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

    private val invalidInput = """
        eyr:1972 cid:100
        hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

        iyr:2019
        hcl:#602927 eyr:1967 hgt:170cm
        ecl:grn pid:012533040 byr:1946

        hcl:dab227 iyr:2012
        ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

        hgt:59cm ecl:zzz
        eyr:2038 hcl:74454a iyr:2023
        pid:3556412378 byr:2007
    """.trimIndent()

    private val validInput = """
        pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
        hcl:#623a2f

        eyr:2029 ecl:blu cid:129 byr:1989
        iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

        hcl:#888785
        hgt:164cm byr:2001 iyr:2015 cid:88
        pid:545766238 ecl:hzl
        eyr:2022

        iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
    """.trimIndent()

    private val solver = DayFour2020(input)
    private val passport = Passport(mapOf())

    @Test fun testEx1() {
        assertEquals(2, solver.partOne())
    }

    @Test fun testPartTwoInvalid() {
        val solver2 = DayFour2020(invalidInput)
        assertEquals(0, solver2.partTwo())
    }

    @Test fun testPartTwoValid() {
        val solver2 = DayFour2020(validInput)
        assertEquals(4, solver2.partTwo())
    }

    @Test fun testByrValid() {
        assertTrue(passport.isValidByr("2002"))
        assertFalse(passport.isValidByr("2003"))
    }

    @Test fun testHgt() {
        assertTrue(passport.isValidHgt("60in"))
        assertTrue(passport.isValidHgt("190cm"))
        assertFalse(passport.isValidHgt("190in"))
        assertFalse(passport.isValidHgt("190"))
    }

    @Test fun testHcl() {
        assertTrue(passport.isValidHcl("#123abc"))
        assertFalse(passport.isValidHcl("#123abz"))
        assertFalse(passport.isValidHcl("123abc"))
    }

    @Test fun testEcl() {
        assertTrue(passport.isValidEcl("brn"))
        assertFalse(passport.isValidEcl("wat"))
    }

    @Test fun testPid() {
        assertTrue(passport.isValidPid("000000001"))
        assertFalse(passport.isValidPid("0123456789"))
    }
}
