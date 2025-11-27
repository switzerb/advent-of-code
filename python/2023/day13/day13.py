# --- Day 13: Point of Incidence ---
# https://adventofcode.com/2023/day/13
import time


def solution(data, smudge=0):
    def dist(l: str, r: str) -> int:
        return sum(a != b for a, b in zip(l, r))

    def reflection(block) -> int:
        for idx in range(len(block)):
            if idx == 0:  # no reflection on first row
                continue
            search = zip(reversed(block[:idx]), block[idx:])
            distances = [dist(l, r) for l, r in search]
            if sum(distances) == smudge:
                return idx
        return 0

    def counter(block):
        rows = block.split("\n")
        rows = [each.strip() for each in rows]
        row = reflection(rows)
        col = reflection(list(zip(*rows)))
        if row: return 100 * row
        if col: return col

    return sum(counter(block) for block in data)


def part_one(data):
    return solution(data, 0)


def part_two(data):
    return solution(data, 1)


def main():
    filename = open("input.txt")
    data = filename.read().split("\n\n")
    print(part_one(data))  # 27202
    print(part_two(data))  # 41566


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
