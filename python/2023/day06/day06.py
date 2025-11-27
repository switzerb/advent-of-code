# --- Day 6: Wait For It ---
# https://adventofcode.com/2023/day/6
import time
from math import prod


def part_one(races):
    # one millisecond == 1 millimeter
    counts = []
    for race in races:
        duration, record = race
        winners = []
        for rate in range(duration):
            distance = (duration - rate) * rate
            if distance > record:
                winners.append(distance)
        counts.append(len(winners))
    return prod(counts)


def part_two(race):
    count = 0
    duration, record = race
    for rate in range(duration):
        distance = (duration - rate) * rate
        if distance > record:
            count += 1
    return count


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    races = [
        (41, 249),
        (77, 1362),
        (70, 1127),
        (96, 1011),
    ]
    race = (41777096, 249136211271011)
    print(part_one(races))  # 771628
    print(part_two(race))  # 27363861


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
