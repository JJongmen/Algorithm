import sys

T = int(sys.stdin.readline())

dp = [0] * 10001
dp[0] = 1
for i in range(1, 4):
    for j in range(i, 10001):
        dp[j] += dp[j - i]

for _ in range(T):
    n = int(sys.stdin.readline())
    print(dp[n])