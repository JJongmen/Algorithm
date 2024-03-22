def solution(triangle):
    dp = [triangle[i][:] for i in range(len(triangle))]
    for i in range(len(triangle) - 1):
        for j in range(i + 1):
            dp[i + 1][j] = max(dp[i + 1][j], triangle[i + 1][j] + dp[i][j])
            dp[i + 1][j + 1] = max(dp[i + 1][j + 1], triangle[i + 1][j + 1] + dp[i][j])
    return max(dp[len(triangle) - 1])