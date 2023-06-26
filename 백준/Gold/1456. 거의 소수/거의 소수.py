import sys

A, B = map(int, sys.stdin.readline().rstrip().split())

K = (int(B ** 0.5) + 1)
isPrime = [True] * K
isPrime[0] = isPrime[1] = False
for i in range(2, K):
    if not isPrime[i]: continue
    for j in range(i + i, K, i):
        isPrime[j] = False

cnt = 0
for i in range(2, K):
    if not isPrime[i]: continue
    tmp = i * i
    while tmp <= B:
        if tmp >= A:
            cnt += 1
        tmp *= i
print(cnt)