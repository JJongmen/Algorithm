import sys

T = int(sys.stdin.readline())

for _ in range(T):
    N = int(sys.stdin.readline())
    for i in range(2, N + 1):
        if N == 1: break
        cnt = 0
        while N % i == 0:
            N /= i
            cnt += 1
        if cnt > 0:
            print(i, cnt)