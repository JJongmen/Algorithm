import sys

MOD = 10**9 + 9

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [sys.stdin.readline().rstrip() for _ in range(N)]
dp = [[1] * M for _ in range(N)]

for i in range(N):
    for j in range(M):
        dp[i][j] %= MOD
        if board[i][j] == 'E':
            dp[i][j + 1] += dp[i][j]
        elif board[i][j] == 'S':
            dp[i + 1][j] += dp[i][j]
        elif board[i][j] == 'B':
            dp[i][j + 1] += dp[i][j]
            dp[i + 1][j] += dp[i][j]
print(dp[N - 1][M - 1])