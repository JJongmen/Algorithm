import bisect

MAX = 1003001
N = int(input())
isPrime = [True] * (MAX + 1)
isPrime[0] = isPrime[1] = False
for i in range(2, int(MAX ** 0.5) + 1):
    if not isPrime[i]:
        continue
    for j in range(i + i, MAX + 1, i):
        isPrime[j] = False
primes = [i for i in range(MAX + 1) if isPrime[i]]
idx = bisect.bisect_left(primes, N)
for i in range(idx, len(primes)):
    prime = primes[i]
    if str(prime) == str(prime)[::-1]:
        print(prime)
        exit()