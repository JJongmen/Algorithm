import sys

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().rstrip().split()))

P = [0] * N
B = sorted(enumerate(A), key=lambda x: x[1])
for i in range(N):
    P[B[i][0]] = i
print(*P)