import sys

N = int(sys.stdin.readline())
requests = list(map(int, sys.stdin.readline().rstrip().split()))
M = int(sys.stdin.readline())

def fn(upperBudget):
    return sum(map(lambda x: min(x, upperBudget), requests)) <= M

lo = 1
hi = max(requests)
while lo < hi:
    mid = (lo + hi) // 2
    if fn(mid):
        lo = mid + 1
    else:
        hi = mid

if hi == max(requests):
    print(hi)
else:
    print(lo - 1)