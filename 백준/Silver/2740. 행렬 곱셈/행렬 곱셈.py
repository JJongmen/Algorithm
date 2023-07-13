import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
A = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
M, K = map(int, sys.stdin.readline().rstrip().split())
B = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(M)]

C = [[0] * K for _ in range(N)]
for i in range(N):
    for j in range(K):
        for k in range(M):
            C[i][j] += A[i][k] * B[k][j]
for row in C:
    print(*row)