import sys

# 동, 남, 서, 북
dx = (0, 1, 0, -1)
dy = (1, 0, -1, 0)

M, N = map(int, sys.stdin.readline().rstrip().split())
board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(M)]
start = list(map(lambda x:(int(x) - 1), sys.stdin.readline().rstrip().split()))
end = list(map(lambda x:(int(x) - 1), sys.stdin.readline().rstrip().split()))
if start[2] == 1: start[2] = 2
elif start[2] == 2: start[2] = 1
if end[2] == 1: end[2] = 2
elif end[2] == 2: end[2] = 1

dp = [[[float('inf')] * 4 for _ in range(N)] for _ in range(M)]
dp[start[0]][start[1]][start[2]] = 0
que = [(start[0], start[1], start[2])]
while que:
    x, y, d = que.pop(0)
    nx, ny = x, y
    for i in range(3):
        nx += dx[d]
        ny += dy[d]
        if nx < 0 or nx >= M or ny < 0 or ny >= N: break
        if board[nx][ny] == 1: break
        if dp[nx][ny][d] <= dp[x][y][d] + 1: continue
        dp[nx][ny][d] = dp[x][y][d] + 1
        que.append((nx, ny, d))
    if dp[x][y][(d + 1) % 4] > dp[x][y][d] + 1:
        dp[x][y][(d + 1) % 4] = dp[x][y][d] + 1
        que.append((x, y, (d + 1) % 4))
    if dp[x][y][(d + 3) % 4] > dp[x][y][d] + 1:
        dp[x][y][(d + 3) % 4] = dp[x][y][d] + 1
        que.append((x, y, (d + 3) % 4))
print(dp[end[0]][end[1]][end[2]])