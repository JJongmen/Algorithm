import sys

N = int(sys.stdin.readline())

if N == 1:
    print(0)
    exit()

MOD = 1_000_000_000
dp = [0] * (N + 1)
dp[2] = 1
for i in range(3, N + 1):
    dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]) % MOD
print(dp[N])