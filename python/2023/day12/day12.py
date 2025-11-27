import time
from functools import cache


@cache
def find(conditions, groups) -> int:
    if len(groups) == 0:
        return '#' not in conditions
    curr, groups = groups[0], groups[1:]
    remaining = sum(groups)
    res = 0
    for idx in range(len(conditions) - remaining - curr + 1):
        if "#" in conditions[:idx]:
            break
        nxt = idx + curr
        if nxt <= len(conditions) and '.' not in conditions[idx:nxt] and conditions[nxt:nxt + 1] != '#':
            res += find(conditions[nxt + 1:], groups)
    return res


def part_one(data) -> int:
    solution = 0
    for each in data:
        conditions, groups_raw = each.split(" ")
        groups = tuple([int(x) for x in groups_raw.split(",")])
        solution += find(conditions.strip(), groups)
    return solution


def part_two(data):
    solution = 0
    for each in data:
        conditions, groups_raw = each.split(" ")
        groups = tuple([int(x) for x in groups_raw.split(",")])
        solution += find("?".join([conditions] * 5), groups * 5)
    return solution


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    print(part_one(data))  # 7460
    print(part_two(data))  # 6720660274964


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
