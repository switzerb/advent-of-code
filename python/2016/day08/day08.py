"""
--- Day 8: Two-Factor Authentication ---
https://adventofcode.com/2016/day/8
"""
import time
from os import path
from copy import copy
from typing import List

SCRIPT_DIR = path.dirname(__file__)
INPUT_FILE = "input.txt"

Screen = List[List[bool]]


def parse(data):
    instructions = []
    for line in data:
        raw = line.split(" ")
        match raw[0]:
            case "rect":
                w, h = raw[1].split("x")
                instructions.append(("rect", (int(w), int(h))))
            case "rotate":
                d = raw[2][2:]
                instructions.append((raw[1], (int(d), int(raw[4]))))
            case _:
                raise Exception("instruction not found ", raw[0])
    return instructions


def build_screen(w: int, h: int) -> Screen:
    return [[False] * w for _ in range(h)]


def screen_print(s: Screen) -> str:
    if len(s) == 0:
        return ""

    width = len(s[0])
    height = len(s)
    output = ""
    for row in range(height):
        for col in range(width):
            output += "#" if s[row][col] else " "
            if col == width - 1:
                output += "\n"
    return output


def rect(w: int, h: int, screen: Screen) -> Screen:
    for row in range(h):
        for col in range(w):
            screen[row][col] = True
    return screen


def column(screen, idx):
    return [row[idx] for row in screen]


def rotate_col(col: int, by: int, screen: Screen) -> Screen:
    h = len(screen)
    if h == 0:
        raise Exception("screen doesn't have any pixels")
    col_values = column(screen, col)

    for row in range(h):
        screen[(row + by) % h][col] = col_values[row]
    return screen


def rotate_row(row: int, by: int, screen: Screen) -> Screen:
    if len(screen) == 0:
        raise Exception("screen doesn't have any pixels")
    w = len(screen[0])
    if w == 0:
        raise Exception("screen doesn't have any pixels")
    row_values = copy(screen[row])

    for col in range(w):
        screen[row][(col + by) % w] = row_values[col]
    # screen[row] = [screen[row][(col + by) % w] for col in range(w)]
    return screen


def on_count(s: Screen) -> str:
    h = len(s)
    if h == 0:
        return 0
    w = len(s[0])
    if w == 0:
        return 0

    count = 0
    for row in range(h):
        for col in range(w):
            if s[row][col]:
                count += 1
    return count


def part_one(instructions, w, h):
    screen = build_screen(w, h)

    for instr in instructions:
        action, details = instr
        match action:
            case "rect":
                w, h = details
                screen = rect(w, h, screen)
            case "row":
                y, by = details
                screen = rotate_row(y, by, screen)
            case "column":
                x, by = details
                screen = rotate_col(x, by, screen)
            case _:
                raise Exception("action not recognized: ", action)

    print(screen_print(screen))
    return on_count(screen)


def part_two(data):
    screen = build_screen(7, 3)
    rect(3, 2)
    print(screen_print(screen))
    return 0


def main():
    f = path.join(SCRIPT_DIR, INPUT_FILE)
    with open(f, mode="rt") as file:
        data = file.read().splitlines()
    instructions = parse(data)
    print("part one: ", part_one(instructions, 50, 6))
    # print("part two: ", part_two(data))


if __name__ == "__main__":
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
