# --- Day 2: Corruption Checksum ---
# http://adventofcode.com/2017/day/2 #

import functools
import itertools
import math


# read in unique puzzle text
# opens a file and reads into a return value
def get_input():
    fh = open("input.txt")
    file_input = fh.read().strip().splitlines()
    fh.close()
    return file_input


def parse(data):
    return list(map(lambda x: list(map(lambda y: int(y), x.split(','))), data))


# get maximum value in list
# list -> int
def get_max(data):
    return max(data)


# get minimum value in list
# list -> int
def get_min(data):
    return min(data)


def part_any(row_checksum_func):
    return lambda puzzle_input: functools.reduce(
        lambda a, i: a + i,
        map(row_checksum_func, puzzle_input),
        0
    )


def part_two_row_checksum(row):
    combinations = itertools.combinations(sorted(row, key=None, reverse=True), 2)
    return functools.reduce(lambda a, i: a + i, map(check_divide, combinations))


def check_divide(pair):
    high, low = pair
    frac, whole = math.modf(high/low)
    if frac == 0:
        return whole
    else:
        return 0


# my version of hello world: it takes nihilism and despair and returns encouragement
# none -> string
def get_encouragement():
    fh = open("input.txt")
    puzzle_input = fh.read().strip()
    fh.close()
    return puzzle_input


# answer to puzzle part one
# puzzle -> answer
part_one = part_any(lambda x: get_max(x) - get_min(x))
part_two = part_any(part_two_row_checksum)


print("Answer to Part One: ", part_one(parse(get_input())))
print("Answer to Part Two: ", part_two(parse(get_input())))
