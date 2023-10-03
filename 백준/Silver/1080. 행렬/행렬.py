import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
A = [list(map(int, ' '.join(sys.stdin.readline().rstrip()).split())) for _ in range(N)]
B = [list(map(int, ' '.join(sys.stdin.readline().rstrip()).split())) for _ in range(N)]

def flip(x, y):
    for i in range(x, x + 3):
        for j in range(y, y + 3):
            try:
                A[i][j] = 1 - A[i][j]
            except IndexError:
                print(-1)
                exit(0)

result = 0
for i in range(N - 2):
    for j in range(M - 2):
        if A[i][j] != B[i][j]:
            flip(i, j)
            result += 1

for i in range(N):
    for j in range(M):
        if A[i][j] != B[i][j]:
            print(-1)
            exit(0)

print(result)