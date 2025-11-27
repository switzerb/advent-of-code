from unittest import TestCase
import day13


class Test(TestCase):

    def setUp(self):
        self.rows = ["""#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.""",
                     """#...##..#
                     #....#..#
                     ..##..###
                     #####.##.
                     #####.##.
                     ..##..###
                     #....#..#"""]

    def test_part_one(self):
        self.assertEqual(405, day13.part_one(self.rows))

    def test_part_two(self):
        self.assertEqual(400, day13.part_two(self.rows))
