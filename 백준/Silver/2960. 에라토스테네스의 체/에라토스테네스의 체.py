import sys

N, K = map(int, sys.stdin.readline().split())
cnt = 0
isPrime = [True for _ in range(N + 1)]
isPrime[0] = isPrime[1] = False
while cnt < K:
    for i in range(2, N + 1):
        if not isPrime[i]: continue;
        for j in range(i, N + 1, i):
            if isPrime[j]:
                isPrime[j] = False
                cnt += 1
                if (cnt == K):
                    print(j)
                    exit()