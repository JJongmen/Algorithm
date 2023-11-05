import sys
from itertools import combinations

input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().rstrip().split())) for _ in range(N)]

positions = []
for i in range(1, N - 1):
    for j in range(1, N - 1):
        positions.append((i, j))

dx = (0, 0, 0, 1, -1)
dy = (0, 1, -1, 0, 0)
cases = combinations(positions, 3)
result = float('inf')
for case in cases:
    sum_ = 0
    visit = [[False] * N for _ in range(N)]
    possible = True
    for x, y in case:
        if not possible: break
        for d in range(5):
            nx, ny = x + dx[d], y + dy[d]
            if visit[nx][ny]:
                possible = False
                break
            sum_ += board[nx][ny]
            visit[nx][ny] = True
    if possible:
        result = min(result, sum_)

print(result)