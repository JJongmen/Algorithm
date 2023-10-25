import sys

input = sys.stdin.readline

N, M, R = map(int, input().rstrip().split())
A = [list(map(int, input().rstrip().split())) for _ in range(N)]
operations = list(map(int, input().rstrip().split()))

# 상하반전
def operation1():
    global N, M
    for i in range(N//2):
        for j in range(M):
            A[i][j], A[N-i-1][j] = A[N-i-1][j], A[i][j]

# 좌우반전
def operation2():
    global N, M
    for i in range(N):
        for j in range(M//2):
            A[i][j], A[i][M-j-1] = A[i][M-j-1], A[i][j]

# 오른쪽으로 90도 회전
def operation3():
    global N, M, A
    tmp = [[0] * N for _ in range(M)]
    for i in range(N):
        for j in range(M):
            tmp[j][N-1-i] = A[i][j]
    A = tmp
    N, M = M, N


# 왼쪽으로 90도 회전
def operation4():
    global N, M, A
    tmp = [[0] * N for _ in range(M)]
    for i in range(N):
        for j in range(M):
            tmp[M-1-j][i] = A[i][j]
    A = tmp
    N, M = M, N


# 4개의 부분 배열 시계방향 이동
def operation5():
    global N, M, A
    tmp = [[0] * M for _ in range(N)]
    for i in range(N//2):
        for j in range(M//2):
            tmp[i][j+M//2] = A[i][j]
    for i in range(N//2):
        for j in range(M//2, M):
            tmp[i+N//2][j] = A[i][j]
    for i in range(N//2, N):
        for j in range(M//2, M):
            tmp[i][j-M//2] = A[i][j]
    for i in range(N//2, N):
        for j in range(M//2):
            tmp[i-N//2][j] = A[i][j]
    A = tmp

# 4개의 부분 배열 반시계방향 이동
def operation6():
    global N, M, A
    tmp = [[0] * M for _ in range(N)]
    for i in range(N//2):
        for j in range(M//2):
            tmp[i+N//2][j] = A[i][j]
    for i in range(N//2):
        for j in range(M//2, M):
            tmp[i][j-M//2] = A[i][j]
    for i in range(N//2, N):
        for j in range(M//2, M):
            tmp[i-N//2][j] = A[i][j]
    for i in range(N//2, N):
        for j in range(M//2):
            tmp[i][j+M//2] = A[i][j]
    A = tmp

for operation in operations:
    if operation == 1:
        operation1()
    elif operation == 2:
        operation2()
    elif operation == 3:
        operation3()
    elif operation == 4:
        operation4()
    elif operation == 5:
        operation5()
    elif operation == 6:
        operation6()

for i in range(N):
    print(*A[i])