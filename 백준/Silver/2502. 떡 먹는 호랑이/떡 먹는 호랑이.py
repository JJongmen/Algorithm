import sys

D, K = map(int, sys.stdin.readline().rstrip().split())


for A in range(1, K):
    for B in range(A, K):
        fibo = [0, A, B]
        for i in range(3, D + 1):
            fibo.append(fibo[i - 1] + fibo[i - 2])
        if fibo[D] == K:
            print(A)
            print(B)
            exit(0)