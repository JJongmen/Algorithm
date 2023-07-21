import sys

N, L = map(int, sys.stdin.readline().rstrip().split())
points = list(map(int, sys.stdin.readline().rstrip().split()))

cnt = 0
isBlocked = [False] * 1001
points.sort()
for point in points:
    if isBlocked[point]: continue
    cnt += 1
    maxRange = min(1001, point + L)
    for i in range(point, maxRange):
        isBlocked[i] = True
print(cnt)