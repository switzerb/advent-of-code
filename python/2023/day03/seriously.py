from collections import defaultdict
from math import prod
from re import finditer

parts = defaultdict(list)
board = list(open('input.txt'))
chars = {(r, c) for r in range(140)
         for c in range(140)
         if board[r][c] not in '01234566789.'}

for r, row in enumerate(board):
    for m in finditer(r'\d+', row):
        nexts = {(r + s, c + d) for s in (-1, 0, 1)
                 for d in (-1, 0, 1)
                 for c in range(*m.span())}
        for c in nexts & chars:
            parts[c].append(int(m[0]))

print(sum(sum(p) for p in parts.values()),
      sum(prod(p) for p in parts.values() if len(p) == 2))
