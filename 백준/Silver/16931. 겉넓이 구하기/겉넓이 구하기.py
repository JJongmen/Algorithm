import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
shapes = [list(map(int, input().rstrip().split())) for _ in range(N)]

result = 0
# 위, 아래
result += 2 * N * M
# 정면
for j in range(M):
    prev = 0
    for i in range(N - 1, -1, -1):
        result += max(0, shapes[i][j] - prev)
        prev = shapes[i][j]
# 뒷면
for j in range(M):
    prev = 0
    for i in range(N):
        result += max(0, shapes[i][j] - prev)
        prev = shapes[i][j]
# 오른쪽면
for i in range(N):
    prev = 0
    for j in range(M - 1, -1, -1):
        result += max(0, shapes[i][j] - prev)
        prev = shapes[i][j]
# 왼쪽면
for i in range(N):
    prev = 0
    for j in range(M):
        result += max(0, shapes[i][j] - prev)
        prev = shapes[i][j]
print(result)