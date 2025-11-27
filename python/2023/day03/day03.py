# --- Day 3: Gear Ratios ---
# https://adventofcode.com/2023/day/3
import re
import time

from Point import Point

HEIGHT = 1
WIDTH = 3


def invalid(pt) -> bool:
    p = Point(pt)
    if p.x < 0 or p.y < 0 or p.y >= HEIGHT or p.x >= WIDTH:
        return True
    return False


def neighbors(part: list[tuple]) -> list[Point]:
    every_p = []
    for p in part:
        point = Point(p)
        n = point.neighbors()
        every_p += n
    uniques = set(every_p)
    points = [pt for pt in uniques if pt not in part]
    result = [pt for pt in points if not invalid(pt)]
    return result


def part_one(schematic):
    global HEIGHT
    global WIDTH
    HEIGHT = len(schematic)
    WIDTH = len(schematic[0])
    parts = []
    digits = re.compile(r"(\d+)")

    def has_adjacent(neighbors: list[Point]) -> bool:
        for n in neighbors:
            p = Point(n)
            if schematic[p.y][p.x] not in non_symbols:
                return True
        return False

    for idx, line in enumerate(schematic):
        matches = digits.findall(line)
        for match in matches:
            part = []
            start = line.find(match)
            end = start + len(match)
            for p in range(start, end):
                part.append((p, idx))
            adj = neighbors(part)
            if has_adjacent(adj):
                parts.append(int(match))
    return sum(parts)


def part_two(data):
    return 0


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    print(part_one(data))  # 525890 - 525911
    # print(part_two(data))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
