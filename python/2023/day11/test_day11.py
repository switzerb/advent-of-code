from unittest import TestCase
import day11


class Test(TestCase):

    def test_part_one(self):
        data = [
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#.....",
        ]
        self.assertEqual(374, day11.part_one(data))

    def test_part_two(self):
        data = ""
        self.assertEqual(0, day11.part_two(data))
