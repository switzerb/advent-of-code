# https://adventofcode.com/2016/day/7
# --- Day 7: Internet Protocol Version 7 ---
import time
from os import path
import regex

SCRIPT_DIR = path.dirname(__file__)
INPUT_FILE = "input.txt"

abba_match = regex.compile(r"(.)(.)\2\1")
aba_match = regex.compile(r"(\w)(\w)\1")
ip_match = regex.compile(r"(\w+)(?:\[\w*])*")
hypernet_match = regex.compile(r"\[(\w+)\]+")


def contains_aba(seq: str) -> list[str]:
    """An ABA is any three-character sequence which consists of the same character
    twice with a different character between them, such as xyx or aba.
    Returns the three letter string to be able to check BAB, the reverse
    If no matches, returns empty string
    """
    matches = aba_match.findall(seq, overlapped=True)
    if len(matches) == 0: return []
    for idx in range(len(matches)):
        a, b = matches[idx]
        if a == b: continue
        yield b + a + b


def contains_abba(seq: str) -> bool:
    """ Check for whether this string contains an ABBA sequence
    ABBA is an Autonomous Bridge Bypass Annotation, or any
    four-character sequence which consists of a pair of two different characters
    followed by the reverse of that pair, such as xyyx or abba.
    e.g. abba is valid. poop is valid. abab is not. """
    matches = abba_match.findall(seq)
    if len(matches) == 0:
        return False

    a, b = matches[0]
    if a == b:
        return False
    return True


def supports_tls(ip: str) -> bool:
    hypers = hypernet_match.findall(ip)
    supers = ip_match.findall(ip)
    # check the square brackets first, if there are any return false
    for h in hypers:
        if contains_abba(h):
            return False
    for s in supers:
        if contains_abba(s):
            return True
    return False


def supports_ssl(ip: str) -> bool:
    hypers = hypernet_match.findall(ip)
    supers = ip_match.findall(ip)
    for s in supers:
        babs = contains_aba(s)
        for bab in babs:
            for h in hypers:
                if bab in h:
                    return True
    return False


def part_one(ips: list[str]) -> int:
    """counts the number of valid IP that support TLS"""
    count = 0
    for ip in ips:
        if supports_tls(ip): count += 1
    return count


def part_two(ips: list[str]) -> int:
    count = 0
    for ip in ips:
        if supports_ssl(ip): count += 1
    return count


def main():
    filename = path.join(SCRIPT_DIR, INPUT_FILE)
    with open(filename, mode="rt") as f:
        ips = f.read().splitlines()
    print("part one: ", part_one(ips))
    print("part two: ", part_two(ips))


if __name__ == "__main__":
    t1 = time.perf_counter()
    main()
    t2 = time.perf_counter()
    print(f"Execution time: {t2 - t1:0.4f} seconds")
