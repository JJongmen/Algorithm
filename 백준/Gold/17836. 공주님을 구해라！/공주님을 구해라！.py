import sys
from collections import deque

input = sys.stdin.readline

N, M, T = map(int, input().rstrip().split())
board = [list(map(int, input().rstrip().split())) for _ in range(N)]

INF = float('inf')
# dp[i][j][0] = i, j 위치에 그람 없이 도착했을 때 최단 거리
# dp[i][j][1] = i, j 위치에 그람을 가지고 도착했을 때 최단 거리
dp = [[[INF] * 2 for _ in range(M)] for _ in range(N)]


if board[0][0] == 2:
    start = (0, 0, 1)
    dp[0][0][1] = 0
else:
    start = (0, 0, 0)
    dp[0][0][0] = 0
que = deque([start])
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
while que:
    x, y, canBreak = que.popleft()
    for d in range(4):
        nx, ny = x + dx[d], y + dy[d]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
        if board[nx][ny] == 2:
            canBreak = 1
            dp[nx][ny][1] = dp[x][y][0] + 1
            que.append((nx, ny, 1))
            continue
        if board[nx][ny] == 1 and canBreak == 0: continue
        if dp[nx][ny][canBreak] > dp[x][y][canBreak] + 1:
            dp[nx][ny][canBreak] = dp[x][y][canBreak] + 1
            que.append((nx, ny, canBreak))

min_ = min(dp[N - 1][M - 1])
if min_ <= T:
    print(min_)
else:
    print('Fail')