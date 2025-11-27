from unittest import TestCase
import day10


class Test(TestCase):
    def test_p1_1(self):
        bots = {
            0: [None, None, [("out", 2), ("out", 0)]],
            1: [None, None, [("out", 1), ("bot", 0)]],
            2: [None, None, [("bot", 1), ("bot", 0)]],
        }
        self.assertEqual(2, day10.part_one(bots, 2, 5))
