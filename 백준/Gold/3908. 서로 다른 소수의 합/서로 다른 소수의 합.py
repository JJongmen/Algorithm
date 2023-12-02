import sys

isPrime = [True] * 1121
isPrime[0] = isPrime[1] = False
primes = []
for i in range(2, 1121):
    if not isPrime[i]: continue
    primes.append(i)
    for j in range(i * i, 1121, i):
        isPrime[j] = False

dp = [[0] * 15 for _ in range(1121)]
dp[0][0] = 1
for prime in primes:
    for i in range(1120, prime - 1, -1):
        for j in range(14, 0, -1):
            dp[i][j] += dp[i - prime][j - 1]

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    n, k = map(int, input().rstrip().split())
    print(dp[n][k])