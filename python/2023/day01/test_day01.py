from unittest import TestCase
import day01


class Test(TestCase):
    def test_p1_1(self):
        inputs = [
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        ]
        self.assertEqual(142, day01.part_one(inputs))

    def test_p1_2(self):
        inputs = [
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"]
        self.assertEqual(281, day01.part_two(inputs))

    def test_p1_3(self):
        inputs = ["pcg91vqrfpxxzzzoneightzt"]
        self.assertEqual(98, day01.part_two(inputs))
