import time
import regex


def part_one(inputs):
    total = 0
    for text in inputs:
        d = regex.findall(r'\d', text)
        if len(d) == 0:
            continue
        if len(d) == 1:
            num = d[0] + d[0]
            total += int(num)
        else:
            num = d[0] + d[-1]
            total += int(num)
    return total


def part_two(inputs):
    words = {
        'one': '1',
        'two': '2',
        'three': '3',
        'four': '4',
        'five': '5',
        'six': '6',
        'seven': '7',
        'eight': '8',
        'nine': '9',
    }
    total = 0
    for text in inputs:
        d = regex.findall(r'(\d|one|two|three|four|five|six|seven|eight|nine)', text, overlapped=True)
        if len(d) == 0:
            continue
        if len(d) == 1:
            first = d[0] if d[0].isnumeric() else words[d[0]]
            num = first + first
            total += int(num)
        else:
            first = d[0] if d[0].isnumeric() else words[d[0]]
            second = d[-1] if d[-1].isnumeric() else words[d[-1]]
            num = first + second
            total += int(num)
    return total


def main():
    filename = open("input.txt")
    data = filename.read().splitlines()
    print(part_one(data))
    print(part_two(data))


if __name__ == '__main__':
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
