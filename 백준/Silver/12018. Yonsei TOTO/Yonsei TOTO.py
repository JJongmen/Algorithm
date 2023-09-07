import sys

n, m = map(int, sys.stdin.readline().rstrip().split())
need_mileages = []
for _ in range(n):
    P, L = map(int, sys.stdin.readline().rstrip().split())
    mileages = list(map(int, sys.stdin.readline().rstrip().split()))
    mileages.sort(reverse=True)
    if P < L:
        need_mileages.append(1)
    else:
        need_mileages.append(mileages[L - 1])
need_mileages.sort()
result = 0
for need_mileage in need_mileages:
    if m >= need_mileage:
        m -= need_mileage
        result += 1
print(result)