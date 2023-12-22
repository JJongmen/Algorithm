import sys

input = sys.stdin.readline

N, L, W, H = map(int, input().rstrip().split())

start, end = 0, max(L, W, H)
for _ in range(10000):
    mid = (start + end) / 2
    if (L // mid) * (W // mid) * (H // mid) < N:
        end = mid
    else:
        start = mid
print(start)