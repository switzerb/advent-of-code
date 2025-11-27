from unittest import TestCase
import day06


class Test(TestCase):

    # blue, red, green
    def test_part_one(self):
        races = [
            (7, 9),
            (15, 40),
            (30, 200)
        ]
        self.assertEqual(288, day06.part_one(races))

    def test_part_two(self):
        race = (71530, 940200)
        self.assertEqual(71503, day06.part_two(race))
