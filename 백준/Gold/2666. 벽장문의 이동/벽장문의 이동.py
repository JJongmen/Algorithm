import sys

INF = 10000

n = int(sys.stdin.readline())
one, two = map(int, sys.stdin.readline().rstrip().split())
length = int(sys.stdin.readline())
order = [int(sys.stdin.readline()) for _ in range(length)]

dp = [[[INF] * 21 for _ in range(21)] for _ in range(21)]

def dfs(i, one, two):
    if i == length:
        return 0
    if dp[i][one][two] != INF:
            return dp[i][one][two]
    dp[i][one][two] = min(
        dfs(i + 1, order[i], two) + abs(one - order[i]),
        dfs(i + 1, one, order[i]) + abs(two - order[i])
    )
    return dp[i][one][two]
print(dfs(0, one, two))