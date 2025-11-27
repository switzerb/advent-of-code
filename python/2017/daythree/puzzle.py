# --- Day 3: Spiral Memory ---
# http://adventofcode.com/2017/day/3

import functools
import copy


def move_right(current):
    return {'dir': 'RIGHT', 'num': current['num'], 'pos': [current['pos'][0] + 1, current['pos'][1]]}


def move_left(current):
    return {'dir': 'LEFT', 'num': current['num'], 'pos': [current['pos'][0] - 1, current['pos'][1]]}


def move_up(current):
    return {'dir': 'UP', 'num': current['num'], 'pos': [current['pos'][0], current['pos'][1] + 1]}


def move_down(current):
    return {'dir': 'DOWN', 'num': current['num'], 'pos': [current['pos'][0], current['pos'][1] - 1]}


def up_available(current, grid):
    return move_up(current) not in grid


def left_available(current, grid):
    return move_left(current) not in grid


def down_available(current, grid):
    return move_down(current) not in grid


def right_available(current, grid):
    return move_right(current) not in grid


def draw_grid():

    # R1 U1 L2 D2 R3 U3 L4 D4 R5

    def move(grid, count):
        curr = copy.copy(grid[len(grid) - 1])

        if count == 0:
            return grid + [{'dir': 'RIGHT', 'num': 2, 'pos': [1, 0]}]

        if curr['dir'] == 'RIGHT':
            # if no box up, go up, otherwise keep going right
            curr = copy.copy(move_up(curr) if up_available(curr, grid) else move_right(curr))

        elif curr['dir'] == 'UP':
            # if no box left, go left, otherwise keep going up
            curr = copy.copy(move_left(curr) if left_available(curr, grid) else move_up(curr))

        elif curr['dir'] == 'LEFT':
            # if no box down, go down, otherwise keep going left
            curr = copy.copy(move_down(curr) if down_available(curr, grid) else move_left(curr))

        elif curr['dir'] == 'DOWN':
            # if no box right, go right, otherwise keep going down
            curr = copy.copy(move_right(curr) if right_available(curr, grid) else move_down(curr))

        curr['num'] += 1
        print(grid + [curr])
        return grid + [curr]

    return list(functools.reduce(move, range(0, 10), [{'dir': 'RIGHT', 'num': 1, 'pos': [0, 0]}]))


# read in unique puzzle text
# opens a file and reads into a return value
def get_input():
    fh = open("input.txt")
    file_input = fh.read().strip()
    fh.close()
    return file_input


# answer to puzzle part one
# puzzle -> answer
def part_one(puzzle_input):
    return list(map(lambda x: x.split(' '), puzzle_input))


# answer to puzzle part two
# puzzle -> answer
def part_two():
    pass


print(draw_grid())

# 1 (0, 0)
# 2 (1, 0)
# 3 (1, 1)
# 4 (0, 1)
# 5 (-1, 1)
# 6 (-1, 0)
# 7 (-1, -1)
# 8 (-1, 0)
# 9 (1, -1)

# n = 1
# (start) (right + n) (up + n) (left + n+1) (down + n+1) (right + n+2) (up + n+2) (left + n+4) (down + n+4) (right + n+5)
