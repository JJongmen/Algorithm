import sys

input = sys.stdin.readline

N = int(input())
nums = []
for _ in range(N):
    a, b = map(int, input().rstrip().split())
    nums.append(b - a)

if len(nums) % 2 == 1:
    print(1)
    exit()

nums.sort()
ans = 0
mid = len(nums) // 2 - 1
print(nums[mid + 1] - nums[mid] + 1)