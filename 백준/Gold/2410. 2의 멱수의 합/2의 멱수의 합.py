import sys
from math import log2
from math import floor

def solve():
    input = sys.stdin.readline

    N = int(input())

    MOD = 1000000000

    dp = [0] * (N + 1)
    dp[1] = 1
    for i in range(2, N + 1):
        dp[i] = dp[i - 1]
        dp[i] += dp[i // 2] if i % 2 == 0 else 0
        dp[i] %= MOD
    print(dp[N])
solve()