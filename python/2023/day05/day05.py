# --- Day 2: Cube Conundrum ---
# https://adventofcode.com/2023/day/3
import re
import time
from functools import reduce
from itertools import chain
from re import findall


def build_almanac(data):
    a = {}
    seeds, *sections = data.strip().split("\n\n")
    a["seeds"] = [int(x) for x in seeds[6:].strip().split(" ")]
    for section in sections:
        lines = section.split("\n")
        key = lines[0][:-5]
        info = []
        for line in lines[1:]:
            info.append(tuple(map(int, line.split(" "))))
        a[key] = info
    return a


def convert(info, target):
    for each in info:
        source = range(each[1], each[1] + each[2])
        destination = range(each[0], each[0] + each[2])
        if target in source:
            # lookup is taking too long! need to trim the amount of work getting done
            lookup = {a: b for a, b in zip(source, destination)}
            return lookup[target]
    return target


def part_one(data):
    almanac = build_almanac(data)
    locations = []
    for seed in almanac["seeds"]:
        soil = convert(almanac["seed-to-soil"], seed)
        fertilizer = convert(almanac["soil-to-fertilizer"], soil)
        water = convert(almanac["fertilizer-to-water"], fertilizer)
        light = convert(almanac["water-to-light"], water)
        temperature = convert(almanac["light-to-temperature"], light)
        humidity = convert(almanac["temperature-to-humidity"], temperature)
        location = convert(almanac["humidity-to-location"], humidity)
        locations.append(location)
    return min(locations)


def part_two(data):
    return 0


def main():
    filename = open("input.txt")
    data = filename.read()
    print(part_one(data))  # 309796150
    # print(part_two(data))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
