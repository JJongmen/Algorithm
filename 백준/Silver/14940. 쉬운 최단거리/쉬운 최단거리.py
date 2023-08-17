import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [[0] * M for _ in range(N)]
dp = [[-1] * M for _ in range(N)]
que = []
for i in range(N):
    board[i] = list(map(int, sys.stdin.readline().rstrip().split()))
    for j in range(M):
        if board[i][j] == 2:
            que.append((i, j))
            dp[i][j] = 0
        elif board[i][j] == 0:
            dp[i][j] = 0

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)
while que:
    x, y = que.pop(0)
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
        if dp[nx][ny] >= 0: continue
        dp[nx][ny] = dp[x][y] + 1
        que.append((nx, ny))

for i in range(N):
    print(*dp[i])
