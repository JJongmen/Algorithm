def solution(m, n, puddles):
    MOD = 1_000_000_007
    dp = [[0] * (m + 1) for _ in range(n + 1)]
    dp[0][0] = 1
    for i in range(n):
        for j in range(m):
            if [j + 1, i + 1] in puddles: continue
            dp[i][j] %= MOD
            dp[i][j + 1] += dp[i][j]
            dp[i + 1][j] += dp[i][j]
    return dp[n - 1][m - 1]