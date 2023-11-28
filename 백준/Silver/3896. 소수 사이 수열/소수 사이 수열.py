import sys
import bisect

input = sys.stdin.readline

MAX = 1299709
isPrime = [True] * (MAX + 1)
isPrime[0] = isPrime[1] = False
primes = []

for i in range(2, MAX + 1):
    if not isPrime[i]: continue
    primes.append(i)
    for j in range(i * i, MAX + 1, i):
        isPrime[j] = False

T = int(input())
for _ in range(T):
    k = int(input())
    print(primes[bisect.bisect_left(primes, k)] - primes[bisect.bisect_right(primes, k) - 1])