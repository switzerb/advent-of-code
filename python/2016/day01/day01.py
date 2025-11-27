from library.Grid import Direction
from library.Point import Point
from copy import copy

origin = Point(0, 0, Direction.NORTH)


def parse(input):
    arr = input.split(",")
    return list(map(lambda each: each.strip(), arr))


def part_one(input):
    current = copy(origin)
    for ins in input:
        turn_to = ins[0]
        dist = int(ins[1:])
        current = current.turn(turn_to)
        current = current.move(turn_to, dist)
    return current.manhattan_distance(origin)


def part_two(puzzle):
    visited = [origin]
    current = copy(origin)
    for ins in puzzle:
        turn_to = ins[0]
        dist = int(ins[1:])
        current = current.turn(turn_to)

        for i in range(0, dist):
            current = current.move(turn_to)
            if current in visited:
                return current.manhattan_distance(origin)
            else:
                visited.append(copy(current))
    return None


file = open("input.txt")
puzzle = parse(file.read())
print("part one: ", part_one(puzzle))
print("part two: ", part_two(puzzle))
