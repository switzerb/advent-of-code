from unittest import TestCase
import day03


class Test(TestCase):

    def test_p1_1(self):
        schematic = """
467.
...*
""".strip().splitlines()
        self.assertEqual(467, day03.part_one(schematic))

    def test_p1_2(self):
        part = [(0, 0), (1, 0), (2, 0)]  # 35
        expected = [
            (0, 1),
            (1, 1),
            (2, 1),
            (3, 1),
            (3, 0),
        ]
        self.assertEqual(set(expected), set(day03.neighbors(part)))

    def test_p1_3(self):
        schematic = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""".strip().splitlines()
        self.assertEqual(4361, day03.part_one(schematic))

    def test_neighbors(self):
        engine = [(2, 2), (3, 2)]  # 35
        neighbors = [
            (1, 1),
            (2, 1),
            (3, 1),
            (4, 1),
            (1, 2),
            (4, 2),
            (4, 3),
            (3, 3),
            (2, 3),
            (1, 3),
        ]
        self.assertEqual(set(neighbors), set(day03.neighbors(engine)))

    def test_part_two(self):
        data = ""
        self.assertEqual(0, day03.part_two(data))
