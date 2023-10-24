import sys

input = sys.stdin.readline

N, M, R = map(int, input().rstrip().split())
A = [list(map(int, input().rstrip().split())) for _ in range(N)]

# R번 회전
for _ in range(R):
    depth = min(N + 1, M + 1) // 2
    for x in range(depth):
        y = x
        tmp = A[x][y]
        for i in range(y, M-y-1):
            A[x][i] = A[x][i+1]
        for i in range(x, N-x-1):
            A[i][M-y-1] = A[i+1][M-y-1]
        for i in range(M-y-1, y, -1):
            A[N-x-1][i] = A[N-x-1][i-1]
        for i in range(N-x-1, x, -1):
            A[i][y] = A[i-1][y]
        A[x+1][y] = tmp

for i in range(N):
    print(*A[i])