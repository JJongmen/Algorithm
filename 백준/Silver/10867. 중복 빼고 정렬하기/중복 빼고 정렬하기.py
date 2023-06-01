import sys

N = int(sys.stdin.readline())
nums = set(map(int, sys.stdin.readline().rstrip().split()))
nums = list(nums)
nums.sort()
print(*nums)