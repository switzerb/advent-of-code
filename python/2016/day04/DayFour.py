import unittest
from itertools import islice
import re
import string
from collections import Counter
from operator import itemgetter



puzzle_input = open("inputs/day_04_input.txt").read().strip()
example_input = 'aaaaa-bbb-z-y-x-123[abxyz]\na-b-c-d-e-f-g-h-987[abcde]\nnot-a-real-room-404[oarel]\ntotally-real-room-200[decoy]'


def parse_file(x):
    split = x.split('\n')
    parsed = map(parse_lines, split)
    return parsed


def parse_lines(x):
    return x[0:-11], int(x[-10:-7]), x[-6:-1]


def remove_dashes(x):
    return re.sub(r'-', "", x)


# count_characters(str[]):[{'str': int}]
def count_characters(name):
    frequency = Counter(name)
    return frequency


# sort_frequency(list)
def sort_frequency(name):
    return sorted(name, key=itemgetter(1), reverse=True)


# sort_alpha(counter):list
def sort_alpha(name):
    return sorted(name.items(), key=itemgetter(0))


# calculate_checksum(dict):str
def calculate_checksum(name):
    counter = count_characters(name)
    alpha = sort_alpha(counter)
    freq = sort_frequency(alpha)
    take = list(islice(freq, 5))
    painstaking_concat = take[0][0] + take[1][0] + take[2][0] + take[3][0] + take[4][0]
    return ''.join(painstaking_concat)


# match_checksum(str,str):bool
def match_checksum(name, checksum):
    return True if name == checksum else False


# create boolean so that you can inline this call
# filter_rooms(str):(str,int,str)[]
def filter_rooms(rooms):
    return filter(lambda f: match_checksum(calculate_checksum(f[0]), f[2]), rooms)


def get_sectors(rooms):
    parsed = parse_file(rooms)
    nodashes = map(lambda a: (remove_dashes(a[0]), a[1], a[2]), parsed)
    real = filter_rooms(nodashes)
    sectors = map(lambda x: x[1], real)
    return sum(sectors)


def decrypt_letter(letter, sectorid):
    if letter == '-':
        return ' '
    else:
        position = (string.lowercase.index(letter) + sectorid) % 26
        return string.lowercase[position]


# decrypt_room( (str, int) ): (str, int)
def decrypt_room(room):
    decrypted_name = map(lambda x: decrypt_letter(x, room[1]), room[0])
    return ''.join(decrypted_name), room[1]


# print get_sectors(puzzle_input)

# print map(lambda x: decrypt_room((x[0], x[1])), parse_file(puzzle_input))


class TestDayFour(unittest.TestCase):

    def test_parse_file(self):
        self.assertEquals(parse_file(example_input), [('aaaaa-bbb-z-y-x', 123, 'abxyz'), ('a-b-c-d-e-f-g-h', 987, 'abcde'), ('not-a-real-room', 404, 'oarel'), ('totally-real-room', 200, 'decoy')])

    def test_remove_dashes(self):
        self.assertEquals(remove_dashes('aaaaa-bbb-z-y-x'), 'aaaaabbbzyx')

    def test_count_characters(self):
        self.assertEquals(count_characters('notarealroom'), {'o': 3, 'a': 2, 'r': 2, 'e': 1, 'm': 1, 'l': 1, 'n': 1, 't': 1})

    def test_sort_alpha(self):
        self.assertEquals(sort_alpha(count_characters('notarealroom')), [('a', 2), ('e', 1), ('l', 1), ('m', 1), ('n', 1), ('o', 3), ('r', 2), ('t', 1)])

    def test_sort_frequency(self):
        self.assertEquals(sort_frequency(sort_alpha(count_characters('notarealroom'))), [('o', 3), ('a', 2), ('r', 2), ('e', 1), ('l', 1), ('m', 1), ('n', 1), ('t', 1)])

    def test_calculate_checksum(self):
        self.assertEquals(calculate_checksum(count_characters('aaaaabbbzyx')), 'abxyz')

    def test_calculate_checksum2(self):
        self.assertEquals(calculate_checksum(count_characters('abcdefgh')), 'abcde')

    def test_calculate_checksum3(self):
        self.assertEquals(calculate_checksum(count_characters('notarealroom')), 'oarel')

    def test_get_sector_sum(self):
        self.assertEquals(get_sectors(example_input), 1514)

    def test_decrypt_letter(self):
        self.assertEquals(decrypt_letter('a', 123), 't')

suite = unittest.TestLoader().loadTestsFromTestCase(TestDayFour)
unittest.TextTestRunner(verbosity=2).run(suite)