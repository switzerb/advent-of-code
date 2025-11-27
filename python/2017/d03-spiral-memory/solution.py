import math

from lib.Point import Point

PORT = Point(0, 0)
NEIGHBORS = [
    Point(1, 0),
    Point(1, -1),
    Point(0, -1),
    Point(-1, -1),
    Point(-1, 0),
    Point(-1, 1),
    Point(0, 1),
    Point(1, 1)
]


# takes a square root and returns a coord on spiral
def anchor_coords(n: int, depth=0) -> Point:
    if n == 1:
        return Point(0, 0)
    if n == 2:
        return Point(0, 1)
    else:
        if n % 2 == 0:  # even
            pos = anchor_coords(n - 1, depth + 1)
            return Point(-pos.x, abs(pos.y) + 1)
        else:  # odd
            pos = anchor_coords(n - 1, depth)
            return Point(abs(pos.x) + 1, -pos.y)


def offset_coords(n: int) -> Point:
    # get nearest square root
    root = math.sqrt(n)
    floor = math.floor(root)
    # number is an integer square root
    if root % 1 == 0:
        return anchor_coords(floor)

    ceil = floor + 1
    offset = (ceil ** 2) - n
    anchor = anchor_coords(ceil)
    turn = (floor ** 2) + floor
    if ceil % 2 == 0:  # even
        return Point(anchor.x + offset, anchor.y) if turn < n else Point(anchor.x + floor, anchor.y - (offset - floor))
    else:  # odd
        return Point(anchor.x - offset, anchor.y) if turn < n else Point(anchor.x - floor, anchor.y + (offset - floor))


def part_one(input: int):
    coords = offset_coords(input)
    return coords.manhattan_distance(PORT)


def part_two(input: int):
    target = int(input)
    x, y, dx = 0, 0, 0
    dy = -1
    grid = {}

    while True:
        total = 0
        for offset in NEIGHBORS:
            if Point(x + offset.x, y + offset.y) in grid:
                total += grid[Point(x + offset.x, y + offset.y)]

        # if sum is larger than our input, return it
        if total > target:
            return total

        if Point(x, y) == Point(0, 0):
            grid[Point(0, 0)] = 1
        else:
            grid[Point(x, y)] = total
        if (x == y) or (x < 0 and x == -y) or (x > 0 and x == 1 - y):
            dx, dy = -dy, dx
        x, y = x + dx, y + dy


# print(part_one(361527))  # 326
print(part_two(361527))  # 363010
# answer is 64th square 363010: https://oeis.org/A141481/b141481.txt
