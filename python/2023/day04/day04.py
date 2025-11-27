# --- Day 4: Scratchcards ---
# https://adventofcode.com/2023/day/4
import time


def intersection(l1, l2):
    l = [v for v in l1 if v in l2]
    return l


def get_points(n):
    total = 1
    for i in range(n - 1):
        total = total * 2
    return total


def part_one():
    data = list(open('input.txt'))

    p1 = [0.5] * len(data)
    p2 = [1.0] * len(data)

    for i, line in enumerate(data):
        w, h = map(str.split, line.split('|'))

        for j in range(len(set(w) & set(h))):
            p1[i] *= 2
            p2[i + j + 1] += p2[i]

    for p in p1, p2: print(sum(map(int, p)))


def part_two(cards):
    total_cards = len(cards) + 1
    queue = []
    # cards is a lookup of id to copies
    # first all copies from the original cards
    for card_id, copies in cards.items():
        for card in copies:
            queue.append(card)
    # then process all the copies
    while len(queue) > 0:
        id = queue.pop()
        total_cards += 1
        stack = cards[id]
        for copy in stack:
            queue.append(copy)
    return total_cards


def main():
    print(part_one())
    # print(part_two(bullshit))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
