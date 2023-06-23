import sys

sys.setrecursionlimit(10**5)
MOD = 10007
memo = [[0 for col in range(501)] for row in range(1001)]


def comb(n, k):
    if n - k < k:
        return comb(n, n - k)
    if memo[n][k] > 0:
        return memo[n][k]
    if k == 0:
        memo[n][0] = 1
        return 1
    if k == 1:
        memo[n][1] = n
        return n
    memo[n][k] = (comb(n - 1, k) + comb(n - 1, k - 1)) % MOD
    return memo[n][k]


N, K = map(int, input().split())
print(comb(N, K))