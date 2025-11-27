import unittest
import aoc2017.daythree.puzzle as puzzle


class TestDayThree(unittest.TestCase):

    def test_example_one(self):
        self.assertEqual(puzzle.part_one('1'), 0)

    def test_example_two(self):
        self.assertEqual(puzzle.part_one('12'), 3)

    def test_example_three(self):
        self.assertEqual(puzzle.part_one('23'), 2)

    def test_example_four(self):
        self.assertEqual(puzzle.part_one('1024'), 31)

    def test_move_right(self):
        self.assertEqual(puzzle.move_right({'dir': 'RIGHT', 'num': 1, 'pos': [3, 4]}), {'dir': 'RIGHT', 'num': 1, 'pos': [4, 4]})


