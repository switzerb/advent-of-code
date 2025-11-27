from unittest import TestCase
import solution
from lib.Point import Point


class Test(TestCase):

    # blue, red, green
    def test_part_one(self):
        self.assertEqual(Point(0, 0), solution.anchor_coords(1))
        self.assertEqual(Point(0, 1), solution.anchor_coords(2))
        self.assertEqual(Point(1, -1), solution.anchor_coords(3))
        self.assertEqual(Point(-1, 2), solution.anchor_coords(4))
        self.assertEqual(Point(2, -2), solution.anchor_coords(5))
        self.assertEqual(Point(-2, 3), solution.anchor_coords(6))
        self.assertEqual(Point(3, -3), solution.anchor_coords(7))
        self.assertEqual(Point(-3, 4), solution.anchor_coords(8))
        self.assertEqual(Point(4, -4), solution.anchor_coords(9))

    def test_dist(self):
        self.assertEqual(0, solution.part_one(1))
        self.assertEqual(1, solution.part_one(4))
        self.assertEqual(2, solution.part_one(9))
        self.assertEqual(3, solution.part_one(16))
        self.assertEqual(4, solution.part_one(25))
        self.assertEqual(5, solution.part_one(36))

    def test_offset_odd(self):
        self.assertEqual(Point(0, -1), solution.offset_coords(8))
        self.assertEqual(Point(-1, -1), solution.offset_coords(7))
        self.assertEqual(Point(-1, 0), solution.offset_coords(6))
        self.assertEqual(Point(-1, 1), solution.offset_coords(5))
        self.assertEqual(Point(0, 1), solution.offset_coords(4))
        self.assertEqual(Point(0, 2), solution.offset_coords(15))
        self.assertEqual(Point(1, 2), solution.offset_coords(14))
        self.assertEqual(Point(2, 2), solution.offset_coords(13))
        self.assertEqual(Point(2, 1), solution.offset_coords(12))
        self.assertEqual(Point(2, 0), solution.offset_coords(11))
        self.assertEqual(Point(2, -1), solution.offset_coords(10))

    def test_solution(self):
        self.assertEqual(0, solution.part_one(1))
        self.assertEqual(3, solution.part_one(12))
        self.assertEqual(2, solution.part_one(23))
        self.assertEqual(31, solution.part_one(1024))
