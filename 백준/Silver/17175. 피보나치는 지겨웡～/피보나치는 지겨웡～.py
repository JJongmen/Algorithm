import sys

n = int(input())

dp = [0] * 51
dp[0] = 1
dp[1] = 1
for i in range(2, n + 1):
    dp[i] = 1 + dp[i - 2] + dp[i - 1]
    dp[i] %= 1_000_000_007

print(dp[n])