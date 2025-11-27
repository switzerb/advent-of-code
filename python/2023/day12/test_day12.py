from unittest import TestCase
import day12


class Test(TestCase):

    def test_p1_1(self):
        self.assertEqual(0, day12.part_one('#', ()))
        self.assertEqual(1, day12.part_one('.', ()))
        self.assertEqual(1, day12.part_one('???.###', (1, 1, 3)))

    def test_p1_2(self):
        self.assertEqual(4, day12.part_one('.??..??...?##.', (1, 1, 3)))

    def test_p2_1(self):
        data = [".??..??...?##. 1,1,3"]
        self.assertEqual(16384, day12.part_two(data))

    def test_p2_2(self):
        data = ["???.### 1,1,3"]
        self.assertEqual(1, day12.part_two(data))

    def test_p2_3(self):
        data = ["????.#...#... 4,1,1"]
        self.assertEqual(16, day12.part_two(data))

    def test_p2_4(self):
        data = ["????.######..#####. 1,6,5"]
        self.assertEqual(2500, day12.part_two(data))

    def test_p2_5(self):
        data = ["?###???????? 3,2,1"]
        self.assertEqual(506250, day12.part_two(data))
