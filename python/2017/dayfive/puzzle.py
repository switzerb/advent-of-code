# --- Day 5: A Maze of Twisty Trampolines, All Alike ---
# http://adventofcode.com/2017/day/5 #

import functools
import itertools

example = [0, 3, 0, 1, -3]


# read in unique puzzle text
# opens a file and reads into a return value
def get_input():
    fh = open("input.txt")
    file_input = fh.read().strip()
    fh.close()
    return file_input


def parse(p_input):
    return list(map(lambda x: int(x), p_input.splitlines()))


def part_any(rule, input):
    answer = 0
    current = 0
    i = 0
    p_input = input[:]

    while True:
        try:
            temp = current
            current += p_input[current]
            p_input[temp] += rule(p_input[temp])
        except IndexError:
            answer = i
            break
        i += 1
    return answer


part_one = part_any(lambda x: 1, parse(get_input()))
part_two = part_any(lambda offset: -1 if offset >= 3 else 1, parse(get_input()))

print(part_one)
print(part_two)
