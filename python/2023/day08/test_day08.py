from unittest import TestCase
import day08


class Test(TestCase):

    def test_p1_1(self):
        data = (
            "RL",
            {
                "AAA": ("BBB", "CCC"),
                "BBB": ("DDD", "EEE"),
                "CCC": ("ZZZ", "GGG"),
                "DDD": ("DDD", "DDD"),
                "EEE": ("EEE", "EEE"),
                "GGG": ("GGG", "GGG"),
                "ZZZ": ("ZZZ", "ZZZ"),
            }
        )
        self.assertEqual(2, day08.part_one(data))

    def test_p1_2(self):
        data = (
            "LLR",
            {
                "AAA": ("BBB", "BBB"),
                "BBB": ("AAA", "ZZZ"),
                "ZZZ": ("ZZZ", "ZZZ"),
            }
        )
        self.assertEqual(6, day08.part_one(data))

    def test_p2_1(self):
        data = (
            "LR",
            {
                "11A": ("11B", "XXX"),
                "11B": ("XXX", "11Z"),
                "11Z": ("11B", "XXX"),
                "22A": ("22B", "XXX"),
                "22B": ("22C", "22C"),
                "22C": ("22Z", "22Z"),
                "22Z": ("22B", "22B"),
                "XXX": ("XXX", "XXX")
            }
        )
        self.assertEqual(6, day08.part_two(data))

    def test_p2_2(self):
        self.assertEqual(False, day08.is_end("AAB"))
        self.assertEqual(True, day08.is_end("AAZ"))
        self.assertEqual(False, day08.is_end("11Z"))
