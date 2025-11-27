import itertools


# read in unique puzzle text
# opens a file and reads into a return value
def get_input():
    fh = open("input.txt")
    file_input = fh.read().strip()
    fh.close()
    return parse(file_input)


def parse(file_input):
    return list(map(lambda x: x.split(' '), file_input.splitlines()))


def create_pairs(passcode):
    return list(itertools.combinations(passcode, 2))


def get_answer(valid_pass):
    return lambda passcodes: len(list(filter(lambda x: not x, map(valid_pass, map(create_pairs, passcodes)))))


def check_validity_part_one(passcode):
    def is_equal(pair):
        a, b = pair
        return a == b
    return any(list(map(is_equal, passcode)))


# answer to puzzle part two
# puzzle -> answer
def check_validity_part_two(passcode):
    def is_anagram(pair):
        a, b = pair
        return sorted(a) == sorted(b)
    return any(list(map(is_anagram, passcode)))


part_one = get_answer(check_validity_part_one)
part_two = get_answer(check_validity_part_two)


print("Answer to Part One: ", part_one(get_input()))
print("Answer to Part Two: ", part_two(get_input()))