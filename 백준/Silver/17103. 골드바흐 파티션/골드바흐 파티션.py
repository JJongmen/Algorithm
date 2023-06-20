import sys

MAX = 1000000

T = int(sys.stdin.readline())

# 소수 구하기
isPrime = [True] * (MAX + 1)
isPrime[0] = isPrime[1] = False
k = int(MAX ** 0.5)
for i in range(2, k + 1):
    if not isPrime[i]: continue
    for j in range(2 * i, MAX + 1, i):
        isPrime[j] = False

primes = [i for i in range(2, MAX + 1) if isPrime[i]]

def partition(N):
    count = 0
    idx = 0
    while idx < len(primes) and primes[idx] <= N / 2:
        if isPrime[N - primes[idx]]: count += 1
        idx += 1
    return count

for _ in range(T):
    N = int(sys.stdin.readline())
    print(partition(N))