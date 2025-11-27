import unittest
import sys
sys.setrecursionlimit(8000)

puzzle_input = open("inputs/day_03_input.txt").read().strip()

example_input = "5 10 25"
example_part2 = [[101, 301, 501],
                 [102, 302, 502],
                 [103, 303, 503],
                 [201, 401, 601],
                 [202, 402, 602],
                 [203, 403, 603]]

parsed_example = [800, 132, 710]


# parse_file(str):int[][]
def parse_file(x):
    lines = x.split('\n')
    stripped = map(lambda a: a.strip(), lines)
    return map(parse_line, stripped)


# parse_line(str):int[]
def parse_line(x):
    return map(lambda a: int(a), x.split(' '))


# parse_file(str):int[][]
def parse_file_sorted(x):
    lines = x.split('\n')
    stripped = map(lambda a: a.strip(), lines)
    return map(parse_line_sorted, stripped)


# parse_line(str):int[]
def parse_line_sorted(x):
    to_int_array = map(lambda a: int(a), x.split(' '))
    return sort_line(to_int_array)


# sort_line(int[]):int[]
def sort_line(x):
    return sorted(x)


# sort(int[]):int[]
def sort(triangles):
    return sorted(triangles)


def is_triangle(triangle):
    return True if triangle[0] + triangle[1] > triangle[2] else False


# separate_triangles(int[][]):int[]
def separate_triangles(triangles):
    first = reduce(lambda a, i: a + [i[0]], triangles, [])
    second = reduce(lambda b, j: b + [j[1]], triangles, [])
    third = reduce(lambda c, k: c + [k[2]], triangles, [])
    return first + second + third


# translate_triangles(int[]):int[][]
def translate_triangles(triangle):
    if triangle is []:
        return []
    elif len(triangle) <= 2:
        return triangle
    else:
        return [[triangle[0], triangle[1], triangle[2]]] + translate_triangles(triangle[3:])


def filter_triangles(triangles):
    return filter(lambda x: is_triangle(x), triangles)

# print len(filter_triangles(parse_file_sorted(puzzle_input)))

# print separate_triangles(example_part2)
# print separate_triangles(parse_file(puzzle_input))
# print translate_triangles(separate_triangles(example_part2))
regroup = translate_triangles(separate_triangles(parse_file(puzzle_input)))

regroup_sorted = map(lambda a: sort(a), regroup)
print regroup
print regroup_sorted

print len(filter_triangles(regroup_sorted))


class TestDayThree(unittest.TestCase):

    def test_sort_input(self):
        self.assertEquals(sort([800, 132, 710]), [132, 710, 800])

    def test_parse_line(self):
        self.assertEquals(parse_line_sorted('21 4 894'), [4, 21, 894])

    def test_is_triangle1(self):
        self.assertEquals(is_triangle([132, 710, 800]), True)

    def test_is_triangle2(self):
        self.assertEquals(is_triangle([5, 10, 25]), False)

    def test_is_triangle3(self):
        self.assertEquals(is_triangle([631, 655, 963]), True)

    def test_is_triangle4(self):
        self.assertEquals(is_triangle([101, 102, 103]), True)

    def test_part1(self):
        self.assertEquals(len(filter_triangles(parse_file_sorted(puzzle_input))), 983)

    def test_separate_triangles(self):
        self.assertEquals(separate_triangles(example_part2), [101, 102, 103, 201, 202, 203, 301, 302, 303, 401, 402, 403, 501, 502, 503, 601, 602, 603])

    def test_translate_triangles(self):
        self.assertEquals(translate_triangles(separate_triangles(example_part2)), [[101, 102, 103], [201, 202, 203], [301, 302, 303], [401, 402, 403], [501, 502, 503], [601, 602, 603]])

    def test_translate_triangles2(self):
        self.assertEquals(translate_triangles([1, 2, 3, 4, 5, 6, 7, 8, 9]), [[1, 2, 3], [4, 5, 6], [7, 8, 9]])

    def test_filter_triangles(self):
        self.assertEquals(filter_triangles([[101, 102, 103], [201, 202, 203], [301, 302, 303], [401, 402, 403], [501, 502, 503], [601, 602, 603]]), [[101, 102, 103], [201, 202, 203], [301, 302, 303], [401, 402, 403], [501, 502, 503], [601, 602, 603]])


suite = unittest.TestLoader().loadTestsFromTestCase(TestDayThree)
unittest.TextTestRunner(verbosity=2).run(suite)
