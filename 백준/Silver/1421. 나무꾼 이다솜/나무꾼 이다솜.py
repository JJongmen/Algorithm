import sys

input = sys.stdin.readline

N, C, W = map(int, input().rstrip().split())
woods = [0] * N
for i in range(N):
    woods[i] = int(input())

max_ = max(woods)
result = 0
for L in range(1, max_ + 1):
    K = 0
    cut_counts = 0
    for wood in woods:
        cut_count = wood // L - 1 if wood % L == 0 else wood // L
        if cut_count * C >= (wood - wood % L) * W: continue
        K += wood // L
        cut_counts += cut_count
    result = max(result, K * L * W - cut_counts * C)
print(result)