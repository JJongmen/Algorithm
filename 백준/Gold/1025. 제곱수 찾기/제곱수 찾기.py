import sys
import math

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
board = [input().rstrip() for _ in range(N)]

def isPerfectScore(num):
    return (int(num) ** 0.5) % 1 == 0

def solve(x, y):
    global N, M, board
    res = -1
    for dx in range(-N, N):
        for dy in range(-M, M):
            if dx == 0 and dy == 0: continue
            nx, ny = x, y
            numStr = board[x][y]
            while True:
                nx += dx
                ny += dy
                if isPerfectScore(numStr):
                    res = max(res, int(numStr))
                if nx < 0 or nx >= N or ny < 0 or ny >= M: break
                numStr += board[nx][ny]
    return res

result = -1
for x in range(N):
    for y in range(M):
        result = max(result, solve(x, y))
print(result)