import unittest

import day01


class DayOne(unittest.TestCase):
    def test_p1_1(self):
        self.assertEqual(day01.part_one(["R2", "L3"]), 5)  # add assertion here

    def test_p1_2(self):
        self.assertEqual(day01.part_one(["R2", "R2", "R2"]), 2)  # add assertion here

    def test_p1_3(self):
        self.assertEqual(day01.part_one(["R5", "L5", "R5", "R3"]), 12)

    def test_p1_actual(self):
        file = open("input.txt")
        self.assertEqual( 242, day01.part_one(day01.parse(file.read())))

    def test_p2_1(self):
        self.assertEqual(4, day01.part_two(day01.parse("R8, R4, R4, R8")))

    def test_p2_actual(self):
        file = open("input.txt")
        self.assertEqual(150, day01.part_two(day01.parse(file.read())))

if __name__ == '__main__':
    unittest.main()
