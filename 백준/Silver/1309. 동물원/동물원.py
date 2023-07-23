import sys

N = int(sys.stdin.readline())

dp = [[0] * 3 for _ in range(N + 1)]
dp[1] = [3, 1, 2]
for i in range(2, N + 1):
    dp[i][1] = dp[i - 1][0]
    dp[i][2] = (dp[i - 1][1] * 2 + dp[i - 1][2]) % 9901
    dp[i][0] = (dp[i][1] + dp[i][2]) % 9901

print(dp[N][0])