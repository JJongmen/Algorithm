import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
hx, hy = map(int, input().rstrip().split())
ex, ey = map(int, input().rstrip().split())
board = [list(map(int, input().rstrip().split())) for _ in range(N)]

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
dp = [[[float('inf')] * 2 for _ in range(M)] for _ in range(N)]
dp[hx - 1][hy - 1][0] = 0
que = deque([(hx - 1, hy - 1, 0)])
while que:
    x, y, z = que.popleft()
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
        # 벽인 경우
        if board[nx][ny] == 1:
            if z == 1: continue
            if dp[nx][ny][1] <= dp[x][y][0] + 1: continue
            dp[nx][ny][1] = dp[x][y][0] + 1
            que.append((nx, ny, 1))
        # 벽이 아닌 경우
        if board[nx][ny] == 0:
            if dp[nx][ny][z] <= dp[x][y][z] + 1: continue
            dp[nx][ny][z] = dp[x][y][z] + 1
            que.append((nx, ny, z))
        if dp[ex - 1][ey - 1][0] != float('inf') or dp[ex - 1][ey - 1][1] != float('inf'):
            print(min(dp[ex - 1][ey - 1]))
            exit(0)
print(-1)