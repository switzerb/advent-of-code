# --- Day 1: Inverse Captcha ---
# http://adventofcode.com/2017/day/1 #

import functools
import itertools


# read in unique puzzle text
# opens a file and reads the values into a return value
def get_input():
    fh = open("input.txt")
    input = fh.read().strip()
    fh.close()
    return convert(input)


# converts a list of strings into a list of integers
# list -> list
def convert(data):
    return list(map(lambda x: int(x), data))


# take list of integers and pair them into tuples for comparison
# list -> iterable
def pairwise(puzzle_input):
    # "s -> (s0,s1), (s1,s2), (s2, s3), ..."
    a, b = itertools.tee(puzzle_input)
    next(b, None)
    return zip(a, b)


# returns a list of integers that represents all the matches in a list
# list[int] -> list[int]
def compare(iterator):
    def prune(x):
        prev, current = x
        if prev == current:
            return int(current)
        else:
            return 0
    return map(prune, iterator)


# returns the total sum of all integers that match the next integer in a list. answer to the first part of the puzzle
# list -> int
def part_one(puzzle_input):
    total = functools.reduce(lambda a, i: a + i, compare(pairwise(puzzle_input)))
    if puzzle_input[0] == puzzle_input[-1]:
        total = total + int(puzzle_input[0])
    return total


# returns half the length of a list
# list -> int
def get_halfway(data):
    return int(len(data) / 2)


# returns the sequence elements n times
# iter -> iter
def ncycles(iterable, n):
    return itertools.chain.from_iterable(itertools.repeat(tuple(iterable), n))


# takes a list of numbers and matches them to their pair halfway around the list, looping. if the
# number matches it's pair, return the number. otherwise return 0
# list[num] -> list[num]
def get_matches(data):
    halfway = get_halfway(data)
    cycle_data = list(ncycles(data, 2))

    def get_match(i):
        index, num = i
        match = halfway + int(index)

        if num == cycle_data[match]:
            return num
        else:
            return 0

    return map(get_match, enumerate(data))


# function that sums the numbers in a list of matches numbers. this is the answer to our puzzle
# list[num] -> int
def part_two(puzzle_input):
    return functools.reduce(lambda a, i: a + i, get_matches(puzzle_input))


# my version of hello world: it takes nihilism and despair and returns encouragement
# none -> string
def get_encouragement():
    return "You can do this."


print("Part One Answer: ", part_one(get_input()))
print("Part Two Answer: ", part_two(get_input()))