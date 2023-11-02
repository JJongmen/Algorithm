import sys

input = sys.stdin.readline

R, C, W = map(int, input().rstrip().split())

N = R + W - 1
dp = [[0] * N for _ in range(N)]
dp[0][0] = 1

for i in range(1, N):
    dp[i][0] = 1
    for j in range(1, i + 1):
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]

result = 0
for i in range(W):
    for j in range(i + 1):
        result += dp[R - 1 + i][C - 1 + j]
print(result)