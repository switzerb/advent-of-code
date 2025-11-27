# --- Day 2: Cube Conundrum ---
# https://adventofcode.com/2023/day/2
import time

# only 12 red cubes, 13 green cubes, and 14 blue cubes

blue = 14
red = 12
green = 13

BLUE = 0
RED = 1
GREEN = 2


def get_game_id(game):
    s = game.split(" ")
    return int(s[1][:-1])


def get_round_data(game):
    e = game.split(":")
    return e[1].strip().split(";")


def build_round_data(r):
    round = [0, 0, 0]
    c = r.split(",")
    for each in c:
        count, color = each.strip().split(" ")
        match color:
            case "blue":
                round[0] = int(count)
            case "red":
                round[1] = int(count)
            case "green":
                round[2] = int(count)
    return round


def parse(data):
    games = {}
    for game in data:
        game_id = get_game_id(game)
        games[game_id] = []
        rounds = get_round_data(game)
        for r in rounds:
            round = build_round_data(r)
            games[game_id].append(round)
    return games


def part_one(games):
    total = 0
    for game_id, rounds in games.items():
        is_possible = True
        for round in rounds:
            if round[0] > blue or round[1] > red or round[2] > green:
                is_possible = False
        if is_possible:
            total += game_id
    return total


def min_reqs(rounds, color):
    return max([round[color] for round in rounds if round[color] > 0])


def part_two(games):
    return sum([min_reqs(r, BLUE) * min_reqs(r, RED) * min_reqs(r, GREEN) for r in games.values()])


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    print(part_one(parse(data)))
    print(part_two(parse(data)))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
