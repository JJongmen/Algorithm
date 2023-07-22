import sys

sys.setrecursionlimit(10**6)

N = int(sys.stdin.readline())
board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

memo = [[-1] * N for _ in range(N)]
memo[N - 1][N - 1] = 1

def dfs(x, y):
    if memo[x][y] != -1:
        return memo[x][y]
    if board[x][y] == 0:
        return 0
    nx = x + board[x][y]
    ny = y + board[x][y]
    cnt = 0
    if nx < N:
        cnt += dfs(nx, y)
    if ny < N:
        cnt += dfs(x, ny)
    memo[x][y] = cnt
    return cnt

print(dfs(0, 0))
