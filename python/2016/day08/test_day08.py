from unittest import TestCase
from textwrap import dedent
import day08


class Test(TestCase):
    def test_p1_1(self):
        screen = day08.build_screen(7, 3)
        self.assertEqual(dedent("""\
        .......
        .......
        .......
        """), day08.screen_print(screen))

    def test_p1_2(self):
        screen = day08.build_screen(7, 3)
        result = day08.rect(3, 2, screen)
        self.assertEqual(dedent("""\
        ###....
        ###....
        .......
        """), day08.screen_print(result))

    def test_p1_3(self):
        start = day08.build_screen(7, 3)
        step_1 = day08.rect(3, 2, start)
        result = day08.rotate_col(1, 1, step_1)
        self.assertEqual(dedent("""\
        #.#....
        ###....
        .#.....
        """), day08.screen_print(result))

    def test_p1_4(self):
        start = day08.build_screen(7, 3)
        step_1 = day08.rect(3, 2, start)
        result = day08.rotate_col(1, 8, step_1)
        self.assertEqual(dedent("""\
        ###....
        #.#....
        .#.....
        """), day08.screen_print(result))

    def test_p1_5(self):
        start = day08.build_screen(7, 3)
        print(day08.screen_print(start))

        step_1 = day08.rect(3, 2, start)
        print(day08.screen_print(step_1))

        step_2 = day08.rotate_col(1, 2, step_1)
        print(day08.screen_print(step_2))

        result = day08.rotate_row(0, 1, step_2)
        self.assertEqual(dedent("""\
        .###...
        #.#....
        .#.....
        """), day08.screen_print(result))

    def test_p1_6(self):
        start = day08.build_screen(7, 3)
        step_1 = day08.rect(3, 2, start)
        step_2 = day08.rotate_col(1, 1, step_1)
        result = day08.rotate_row(0, 4, step_2)
        self.assertEqual(dedent("""\
        ....#.#
        ###....
        .#.....
        """), day08.screen_print(result))

    def test_p1_7(self):
        start = day08.build_screen(7, 3)
        step_1 = day08.rect(3, 2, start)
        step_2 = day08.rotate_col(1, 1, step_1)
        step_3 = day08.rotate_row(0, 4, step_2)
        result = day08.rotate_col(1, 1, step_3)
        self.assertEqual(dedent("""\
        .#..#.#
        #.#....
        .#.....
        """), day08.screen_print(result))

    def test_p1_8(self):
        instructions = [
            ("rect", (3, 2)),
            ("column", (1, 1)),
            ("row", (0, 4)),
            ("column", (1, 1)),
        ]
        self.assertEqual(day08.part_one(instructions, 7, 3), 6)

    def test_p1_actual(self):
        filename = open("input.txt")
        instructions = day08.parse(filename.read().splitlines())
        filename.close()
        self.assertEqual(day08.part_one(instructions, 50, 6), 121)
