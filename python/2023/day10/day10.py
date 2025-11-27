# --- Day:  ---
# https://adventofcode.com/2023/day/3
import time


# | is a vertical pipe connecting north and south.
# - is a horizontal pipe connecting east and west.
# L is a 90-degree bend connecting north and east.
# J is a 90-degree bend connecting north and west.
# 7 is a 90-degree bend connecting south and west.
# F is a 90-degree bend connecting south and east.
# . is ground; there is no pipe in this tile.
# S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.


class Solution:
    pipe_map = {}
    directions = {"N": (0, -1), "S": (0, 1), "E": (1, 0), "W": (-1, 0)}
    pipe_dirs = {"|": "NS", "-": "EW", "L": "NE", "J": "NW", "7": "SW", "F": "SE"}
    tube = list()
    entry_char = ""

    def __init__(self, input, entry_char):
        self.entry_char = entry_char
        self.pipe_map, self.width, self.height, self.entry_pt = self.parse(input)

    def parse(data):
        map = {}
        width = 0
        height = 0
        entry_pt = (0, 0)
        for y, line in enumerate(data):
            for x, c in enumerate(line):
                if c == "S":
                    entry_pt = (x, y)
                map[(x, y)] = c
                width = x
            height = y
        return map, width + 1, height + 1, entry_pt

    parse = staticmethod(parse)

    def part_one(self):
        self.tube.append(self.entry_pt)

        def move(p, d):
            return p[0] + self.directions[d][0], p[1] + self.directions[d][1]

        curr = self.entry_pt
        prev = curr
        steps = 0
        while True:
            pipe = self.pipe_map[curr]
            if pipe == "S":
                pipe = self.entry_char
            opt1 = move(curr, self.pipe_dirs[pipe][0])
            opt2 = move(curr, self.pipe_dirs[pipe][1])
            curr = opt2 if prev == opt1 else opt1
            prev = self.tube[-1]
            self.tube.append(curr)
            steps += 1
            if curr == self.entry_pt:
                break
        return steps // 2

    def on_loop(self, curr):
        return True if curr in self.tube else False

    def is_crossover(self, curr):
        pipe = self.pipe_map[curr]
        if pipe == "S":
            pipe = self.entry_char
        n = self.pipe_map[(curr[0] + 1, curr[1])]
        match pipe:
            case "|":
                return True
            case "L":
                return True if n == "7" else False
            case "F":
                return True if n == "J" else False
            case _:
                return False

    def part_two(self):
        self.part_one()  # we need to build the tube
        inside = False  # start outside
        tiles = 0

        for y in range(-1, self.height):
            for x in range(-1, self.width):
                if (x, y) in self.tube:
                    if inside:
                        tiles += 1
                    if self.is_crossover((x, y)):
                        inside = not inside
        return tiles


def main():
    filename = open("input.txt")
    puzzle = Solution(filename.read().splitlines(), "-")
    print(puzzle.part_one())
    print(puzzle.part_two())


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
