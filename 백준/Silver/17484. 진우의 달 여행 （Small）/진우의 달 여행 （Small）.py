import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

dp = [[[1000] * 3 for _ in range(M)] for _ in range(N)]
for j in range(M):
    for d in range(3):
        dp[0][j][d] = board[0][j]
dy = (-1, 0, 1)

for i in range(N - 1):
    for j in range(M):
        for d in range(3):
            for nd in range(3):
                if d == nd: continue
                ny = j + dy[nd]
                if ny < 0 or ny >= M: continue
                dp[i + 1][ny][nd] = min(dp[i + 1][ny][nd], dp[i][j][d] + board[i + 1][ny])
print(min([dp[N - 1][j][d] for j in range(M) for d in range(3)]))