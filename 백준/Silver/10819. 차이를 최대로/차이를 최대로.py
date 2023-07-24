import sys
import itertools

N = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().rstrip().split()))

cases = itertools.permutations(nums)
ans = 0
for case in cases:
    sum = 0
    for i in range(N - 1):
        sum += abs(case[i] - case[i + 1])
    ans = max(ans, sum)
print(ans)