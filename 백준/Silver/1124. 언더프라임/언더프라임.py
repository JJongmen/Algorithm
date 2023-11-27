isPrime = [True] * 100001
isPrime[0] = isPrime[1] = False
for i in range(2, 100001):
    if not isPrime[i]: continue
    for j in range(i * i, 100001, i):
        isPrime[j] = False

primeCnt = [0] * 100001
for i in range(2, 100001):
    if isPrime[i]: primeCnt[i] = 1
    else:
        for j in range(2, i):
            if i % j == 0:
                primeCnt[i] = 1 + primeCnt[i // j]
                break

def isUnderPrime(n):
    return isPrime[primeCnt[n]]

A, B = map(int, input().split())
result = 0
for i in range(A, B + 1):
    result += isUnderPrime(i)
print(result)