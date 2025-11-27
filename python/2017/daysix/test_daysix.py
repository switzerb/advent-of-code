import unittest
import aoc2017.daysix.puzzle as puzzle


class TestDaySix(unittest.TestCase):

    def test_get_encouragement(self):
        self.assertEqual(puzzle.get_encouragement(), "You can do this.")
