import unittest
import collections


# parse_file(str):str[]
def parse_file(x):
    return x.strip().split('\n')


# pivot(str[]):str[][]
def pivot(messages):
    return map(lambda y: map(lambda x: x[y], messages), range(0, len(messages[0])))


# most_common(str[][]): str
def most_common(positions):
    return counter_helper(positions, lambda x: collections.Counter(x).most_common(1))


def least_common(positions):
    return counter_helper(positions, lambda x: collections.Counter(x).most_common()[-1])


# least_common(str[][]): str
def counter_helper(positions, f):
    counter = map(f, positions)
    letters = map(lambda x: x[0][0], counter)
    return ''.join(letters)


def part_one(input):
    return most_common(pivot(parse_file(input)))


def part_two(input):
    return least_common(pivot(parse_file(input)))


class TestDaySix(unittest.TestCase):

    def setUp(self):
        self.example_input = """eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar
"""
        self.puzzle_input = open("inputs/day_06_input.txt").read().strip()

    def test_canvas(self):
        True
        # print least_common(pivot(parse_file(self.example_input)))
        # print most_common(parsed_file)
        # position_one = map(lambda x: x[0], parsed_file)

    def test_parse_file(self):
        self.assertEquals(parse_file(self.example_input), ['eedadn', 'drvtee', 'eandsr', 'raavrd', 'atevrs', 'tsrnev', 'sdttsa', 'rasrtv', 'nssdts', 'ntnada', 'svetve', 'tesnvt', 'vntsnd', 'vrdear', 'dvrsen', 'enarar'])

    def test_pivot(self):
        self.assertEquals(pivot(parse_file(self.example_input)), [['e', 'd', 'e', 'r', 'a', 't', 's', 'r', 'n', 'n', 's', 't', 'v', 'v', 'd', 'e'], ['e', 'r', 'a', 'a', 't', 's', 'd', 'a', 's', 't', 'v', 'e', 'n', 'r', 'v', 'n'], ['d', 'v', 'n', 'a', 'e', 'r', 't', 's', 's', 'n', 'e', 's', 't', 'd', 'r', 'a'], ['a', 't', 'd', 'v', 'v', 'n', 't', 'r', 'd', 'a', 't', 'n', 's', 'e', 's', 'r'], ['d', 'e', 's', 'r', 'r', 'e', 's', 't', 't', 'd', 'v', 'v', 'n', 'a', 'e', 'a'], ['n', 'e', 'r', 'd', 's', 'v', 'a', 'v', 's', 'a', 'e', 't', 'd', 'r', 'n', 'r']])

    def test_most_common(self):
        self.assertEquals(most_common(pivot(parse_file(self.example_input))), 'easter')

    def test_example_one(self):
        self.assertEquals(part_one(self.example_input), 'easter')

    def test_part_one(self):
        self.assertEquals(part_one(self.puzzle_input), 'umcvzsmw')

    def test_part_two(self):
        self.assertEquals(part_two(self.puzzle_input), 'rwqoacfz')

    def test_example_two(self):
        self.assertEquals(part_two(self.example_input), 'advent')

suite = unittest.TestLoader().loadTestsFromTestCase(TestDaySix)
unittest.TextTestRunner(verbosity=2).run(suite)