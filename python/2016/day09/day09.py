# --- Day 9: Explosives in Cyberspace ---
# https://adventofcode.com/2016/day/9
import time
import regex

marker_match = regex.compile(r"\((\d+)x(\d+)\)")


def part_one(input: str) -> int:
    input_end = len(input)
    decompression_len = 0
    pos = 0
    while True:
        marker = regex.search(marker_match, input, 0, pos)
        if marker:
            count = marker.group(1)
            times = marker.group(2)
            decompression_len += (marker.start() - pos)
            decompression_len += int(count) * int(times)
            pos += (marker.end() + int(count) - pos)
        else:
            remainder = input_end - pos
            decompression_len += remainder
            pos += remainder
        if pos >= input_end:
            break
    return decompression_len


def part_two(input: str) -> int:
    input_end = len(input)
    decompression_len = 0
    pos = 0
    while True:
        marker = regex.search(marker_match, input, 0, pos)
        if marker:
            count = marker.group(1)
            times = marker.group(2)
            decompression_len += (marker.start() - pos)
            moar = input[marker.end():marker.end() + int(count)]
            decompression_len += part_two(moar) * int(times)
            pos += (marker.end() + int(count) - pos)
        else:
            remainder = input_end - pos
            decompression_len += remainder
            pos += remainder
        if pos >= input_end:
            break
    return decompression_len


def main():
    filename = open("input.txt")
    data = filename.read()
    print(part_one(data))
    print(part_two(data))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
