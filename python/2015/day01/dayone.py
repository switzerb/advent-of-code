import unittest
import functools
import sys
sys.setrecursionlimit(8000)


# day one part one - what floor do we end up on?
# takes a list of characters and returns an integer
# str -> int
def find_floor(floor_input):
    if len(floor_input) == 1:
        return floor(floor_input)
    else:
        return find_floor(floor_input[1:]) + floor(floor_input[0])


# day one part two - find position of the first character where he enters the basement
# takes a list of characters and returns an integer
# str -> int
def find_position(floor_input):
    result = functools.reduce(lambda a, i: {'position': a['position'] + 1, 'sum': a['sum'] + floor(i), 'basement': (a['sum'] + floor(i)) < 0} if not a['basement'] else a, floor_input, {'position': 0, 'sum': 0, 'basement': False})
    return result['position']


# takes a character that represents a floor and returns an integer
# char -> int
def floor(f):
    if f == ')':
        return -1
    elif f == '(':
        return 1
    else:
        return None


data = open("input.txt").read().strip()
print(find_floor(data))
print(find_position(data))


class TestDayOne(unittest.TestCase):

    def test_example_open(self):
        self.assertEquals(floor('('), 1)

    def test_example_closed(self):
        self.assertEquals(floor(')'), -1)

    def test_example_one(self):
        self.assertEqual(find_floor('(())'), 0)

    def test_example_two(self):
        self.assertEqual(find_floor('()()'), 0)

    def test_example_three(self):
        self.assertEqual(find_floor('((('), 3)

    def test_example_four(self):
        self.assertEqual(find_floor('(()(()('), 3)

    def test_example_five(self):
        self.assertEqual(find_floor('))((((('), 3)

    def test_example_six(self):
        self.assertEqual(find_floor('())'), -1)

    def test_example_seven(self):
        self.assertEqual(find_floor('))('), -1)

    def test_example_eight(self):
        self.assertEqual(find_floor(')))'), -3)

    def test_example_nine(self):
        self.assertEqual(find_floor(')())())'), -3)

    def test_example_ten(self):
        self.assertEqual(find_position(')'), 1)

    def test_example_eleven(self):
        self.assertEqual(find_position('()())'), 5)