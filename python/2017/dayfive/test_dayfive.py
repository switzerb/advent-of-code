import unittest
import aoc2017.dayfive.puzzle as puzzle


class TestDayFive(unittest.TestCase):

    def test_example_one(self):
        self.assertEqual(puzzle.part_one([0, 3, 0, 1, -3]), 5)
