from unittest import TestCase
import day04


class Test(TestCase):

    def test_p1_1(self):
        data = ("""
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
""")
        cards = [([41, 48, 83, 86, 17], [83, 86, 6, 31, 17, 9, 48, 53])]
        self.assertEqual(8, day04.part_one(cards))

    def test_p1_2(self):
        cards = [([41, 48, 83, 86, 17], [83, 86, 6, 31, 17, 9, 48, 53]),
                 ([13, 32, 20, 16, 61], [61, 30, 68, 82, 17, 32, 24, 19]),
                 ([1, 21, 53, 59, 44], [69, 82, 63, 72, 16, 21, 14, 1]),
                 ([41, 92, 73, 84, 69], [59, 84, 76, 51, 58, 5, 54, 83]),
                 ([87, 83, 26, 28, 32], [88, 30, 70, 12, 93, 22, 82, 36]),
                 ([31, 18, 13, 56, 72], [74, 77, 10, 23, 35, 67, 36, 11]),
                 ]
        self.assertEqual(13, day04.part_one(cards))

    def test_part_two(self):
        cards = {
            1: [2, 3, 4, 5],
            2: [3, 4],
            3: [4, 5],
            4: [5],
            5: []
        }
        self.assertEqual(30, day04.part_two(cards))
