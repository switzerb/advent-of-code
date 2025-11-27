from unittest import TestCase
import day09


class Test(TestCase):

    def test_p1_1(self):
        self.assertEqual(18, day09.find_next([0, 3, 6, 9, 12, 15]))

    def test_p1_2(self):
        self.assertEqual(28, day09.find_next([1, 3, 6, 10, 15, 21]))

    def test_p1_3(self):
        self.assertEqual(68, day09.find_next([10, 13, 16, 21, 30, 45]))

    def test_part_one(self):
        data = [
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45"
        ]
        self.assertEqual(114, day09.part_one(day09.parse(data)))

    def test_part_two(self):
        data = ""
        self.assertEqual(0, day09.part_two(data))
