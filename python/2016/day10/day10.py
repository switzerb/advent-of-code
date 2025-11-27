# --- Day 10: Balance Bots ---
# https://adventofcode.com/2016/day/10

import time


def parse(data):
    bots = {}
    for bot in data:
        s = bot.split(" ")
        match s[0]:
            case "value":
                continue
            case "bot":
                bots[int(s[1])] = [None, None, [(s[5], int(s[6])), (s[10], int(s[11]))]]
            case _:
                raise Exception("Don't recognize this", s[0])

    for bot in data:
        s = bot.split(" ")
        match s[0]:
            case "value":
                num = int(s[5])
                if bots[num][0] is None:
                    bots[num][0] = int(s[1])
                else:
                    bots[num][1] = int(s[1])
            case "bot":
                continue
            case _:
                raise Exception("Don't recognize this", s[0])
    return bots


def part_one(bots, of_interest_1, of_interest_2):
    # { bot_id: [c1, c2, low, high] }
    # nonlocal

    out = {}

    def find_full_bot():
        for bot in bots.items():
            b_id, info = bot
            c1, c2 = info[:2]
            if c1 is not None and c2 is not None:
                return bots[b_id], b_id

    def traverse(bot, b_id):
        c1, c2, neighbors = bot
        if c1 is not None and c2 is not None:
            if c1 > c2:
                c2, c1 = c1, c2
            if c1 == of_interest_1 and c2 == of_interest_2:
                return b_id
            hand_off(bot)

    def giveChipAway(b_id, chip):
        if bots[b_id][0] is None:
            bots[b_id][0] = chip
        else:
            bots[b_id][1] = chip
        bot = bots[b_id]
        _, _, neighbors = bot
        for type, num in neighbors:
            if type == "bot":
                traverse(bots[num], num)

    def hand_off(bot):
        c1, c2, neighbors = bot
        low, high = neighbors
        if c1 > c2:
            c2, c1 = c1, c2
        n = low[1]
        m = high[1]
        if low[0] == "bot":
            giveChipAway(n, c1)
        else:
            out[n] = c1
        if high[0] == "bot":
            giveChipAway(m, c2)
        else:
            out[m] = c2

    giveChipAway(2, 5)
    giveChipAway(1, 3)
    giveChipAway(2, 2)

    # start, bot_id = find_full_bot()
    # return traverse(start, bot_id)


def part_two():
    # print(data)
    return 0


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    print(part_one(parse(data), 17, 61))
    # print(part_two())


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
