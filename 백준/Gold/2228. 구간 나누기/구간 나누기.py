import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())

sum_ = [0]
for _ in range(N):
    num = int(input())
    sum_.append(sum_[-1] + num)

dp = [[0] * (M + 1) for _ in range(N + 1)]
for j in range(1, M + 1):
    dp[0][j] = -float('inf')

for i in range(1, N + 1):
    for j in range(1, M + 1):
        dp[i][j] = dp[i - 1][j]
        for k in range(1, i + 1):
            if k >= 2:
                dp[i][j] = max(dp[i][j], dp[k - 2][j - 1] + sum_[i] - sum_[k - 1])
            elif k == 1 and j == 1:
                dp[i][j] = max(dp[i][j], sum_[i])
print(dp[N][M])