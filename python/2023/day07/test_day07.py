from unittest import TestCase
import day07


class Test(TestCase):

    def test_p1_1(self):
        self.assertEqual(7, day07.get_type_rank("AAAAA"))  # "five-of-kind"
        self.assertEqual(6, day07.get_type_rank("AA8AA"))  # "four-of-kind"
        self.assertEqual(5, day07.get_type_rank("23332"))  # "full-house"
        self.assertEqual(4, day07.get_type_rank("TTT98"))  # "three-of-kind"
        self.assertEqual(3, day07.get_type_rank("23432"))  # "two-pair"
        self.assertEqual(2, day07.get_type_rank("A23A4"))  # "one-pair"
        self.assertEqual(1, day07.get_type_rank("23456"))  # "high-card"

    def test_part_one(self):
        data = [
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
        ]
        self.assertEqual(6440, day07.part_one(day07.parse(data)))

    def test_part_two(self):
        data = [
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483",
        ]
        self.assertEqual(5905, day07.part_two(day07.parse(data)))
