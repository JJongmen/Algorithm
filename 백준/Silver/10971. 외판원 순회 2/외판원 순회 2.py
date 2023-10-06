import sys
import itertools

N = int(sys.stdin.readline())
board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
cases = itertools.permutations(range(1, N))

result = float('inf')
for case in cases:
    prev, dist = 0, 0
    possible = True
    for city in case:
        if board[prev][city] == 0:
            possible = False
            break
        dist += board[prev][city]
        prev = city
    if board[prev][0] == 0:
        possible = False
    dist += board[prev][0]
    if possible:
        result = min(result, dist)
print(result)