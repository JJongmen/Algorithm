import sys

input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().rstrip().split()))

one_min = min(nums)
two_min = float('inf')
for i in range(6):
    for j in range(i + 1, 6):
        if i + j == 5: continue
        two_min = min(two_min, nums[i] + nums[j])
three_min = float('inf')
for i in range(6):
    for j in range(i + 1, 6):
        for k in range(j + 1, 6):
            if i + j == 5 or i + k == 5 or j + k == 5: continue
            three_min = min(three_min, nums[i] + nums[j] + nums[k])

if N == 1:
    print(sum(nums) - max(nums))
else:
    one_cnt = (N - 2) * (5 * N - 6) if N > 2 else 0
    two_cnt = 8 * N - 12 if N > 1 else 0
    three_cnt = 4 if N > 1 else 0
    print(one_cnt * one_min + two_cnt * two_min + three_cnt * three_min)
