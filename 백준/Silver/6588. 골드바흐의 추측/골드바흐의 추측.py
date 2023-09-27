import sys

isPrime = [True] * 1000001
for i in range(2, 1000001):
    if not isPrime[i]: continue
    for j in range(i * i, 1000001, i):
        isPrime[j] = False
primes = [i for i in range(3, 1000001, 2) if isPrime[i]]

while True:
    N = int(sys.stdin.readline())

    if N == 0: break

    for prime in primes:
        if not isPrime[N - prime]: continue
        print(f'{N} = {prime} + {N - prime}')
        break
