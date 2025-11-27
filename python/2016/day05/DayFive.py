import unittest
import itertools
import hashlib


example_input = 'abc'
puzzle_input = 'uqwqemis'


# md5_encryption(str):str
def encrypt(s, n):
    m = hashlib.md5()
    value = s + str(n)
    m.update(value)
    return m.hexdigest()


# is_md5(str):bool
def is_interesting(s, n):
    hashed = encrypt(s, n)
    return True if hashed[:5] == '00000' else False


def take(n, iterable):
    return list(itertools.islice(iterable, n))


# search():str
def search(seed):
    indexes = itertools.ifilter(lambda x: is_interesting(seed, x), itertools.count(0, 1))
    return take(8, indexes)


# part_one(str[]):str[]
def part_one(seed):
    indexes = search(seed)
    hashes = map(lambda x: encrypt(seed, x), indexes)
    characters = map(lambda x: x[5], hashes)
    return ''.join(characters)


# is_valid(str):bool
def is_valid_index(ch):
    if ch.isdigit():
        return True if int(ch) < 8 else False
    else:
        return False


# def decrypt(position, code):
#     chain = '--------'
#     if chain[position] == '-':
#         chain[position] = code


def part_two(seed):
    indexes = itertools.ifilter(lambda x: is_interesting(seed, x), itertools.count(0, 1))
    hashes = itertools.imap(lambda x: encrypt(seed, x), indexes)
    valid = itertools.ifilter(lambda f: is_valid_index(f[5]), hashes)
    winnow = itertools.imap(lambda x: (x[5], x[6]), valid)
    keylist = take(15, winnow)
    return keylist



class TestDayFive(unittest.TestCase):

    def test_hello_world(self):
        # test = map(lambda x: x , range(0, 7))
        True

    # def test_encrypt(self):
    #     self.assertEquals(encrypt('abc', 3231929), '00000155f8105dff7f56ee10fa9b9abd')
    #
    # def test_interesting(self):
    #     self.assertEquals(is_interesting('abc', 3231929), True)

    # def test_part_one_example(self):
    #     self.assertEquals(part_one(example_input), '18f47a30')

    # def test_part_one(self):
    #     self.assertEquals(part_one(puzzle_input), '1a3099aa')

    def test_is_valid_1(self):
        self.assertEquals(is_valid_index('1'), True)

    def test_is_valid_2(self):
        self.assertEquals(is_valid_index('f'), False)

    def test_is_valid_3(self):
        self.assertEquals(is_valid_index('9'), False)

    # def test_part_two(self):
    #     self.assertEquals(part_two(puzzle_input), '')

    def test_part_two_example(self):
        self.assertEquals(part_two(puzzle_input), '694190cd')

suite = unittest.TestLoader().loadTestsFromTestCase(TestDayFive)
unittest.TextTestRunner(verbosity=2).run(suite)