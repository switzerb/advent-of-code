from unittest import TestCase
import day02


class Test(TestCase):

    # blue, red, green
    def test_part_one(self):
        # Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        # Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        # Game 3: 6 blue, 20 red, 8 green; 5 blue, 4 red, 13 green; 1 red, 5 green
        # Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        # Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        games = {
            1: [[3, 4, 0], [6, 1, 2], [0, 0, 2]],
            2: [[1, 0, 2], [4, 1, 3], [1, 0, 1]],
            3: [[6, 20, 8], [5, 4, 13], [0, 1, 5]],
            4: [[6, 3, 1], [0, 6, 3], [15, 14, 3]],
            5: [[1, 6, 3], [2, 1, 2]]
        }
        self.assertEqual(8, day02.part_one(games))

    def test_parse(self):
        data = [
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 6 blue, 20 red, 8 green; 5 blue, 4 red, 13 green; 1 red, 5 green",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        ]
        games = {
            1: [[3, 4, 0], [6, 1, 2], [0, 0, 2]],
            2: [[1, 0, 2], [4, 1, 3], [1, 0, 1]],
            3: [[6, 20, 8], [5, 4, 13], [0, 1, 5]],
            4: [[6, 3, 1], [0, 6, 3], [15, 14, 3]],
            5: [[1, 6, 3], [2, 1, 2]]
        }
        self.assertEqual(games, day02.parse(data))

    def test_part_two(self):
        games = {
            1: [[3, 4, 0], [6, 1, 2], [0, 0, 2]],
            2: [[1, 0, 2], [4, 1, 3], [1, 0, 1]],
            3: [[6, 20, 8], [5, 4, 13], [0, 1, 5]],
            4: [[6, 3, 1], [0, 6, 3], [15, 14, 3]],
            5: [[1, 6, 3], [2, 1, 2]]
        }
        self.assertEqual(2286, day02.part_two(games))
