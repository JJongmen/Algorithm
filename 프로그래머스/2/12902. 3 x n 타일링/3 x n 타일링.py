def solution(n):
    if n % 2 == 1:
        return 0
    MOD = 1_000_000_007
    dp = [0] * 5001
    sum_, dp[0], dp[2] = 0, 1, 3
    for i in range(4, n + 1, 2):
        sum_ = (sum_ + dp[i - 4]) % MOD
        dp[i] = (dp[i - 2] * 3 + sum_ * 2) % MOD
    return dp[n]