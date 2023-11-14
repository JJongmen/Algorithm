import sys

input = sys.stdin.readline

N = int(input())
flavors = [list(map(int, input().rstrip().split())) for _ in range(N)]

result = float('inf')
for mask in range(1, 2**N):
    prod_, sum_ = 1, 0
    for i in range(N):
        if mask & (1 << i):
            prod_ *= flavors[i][0]
            sum_ += flavors[i][1]
    result = min(result, abs(prod_ - sum_))
print(result)