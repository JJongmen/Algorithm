import sys
import math
import collections

N = int(sys.stdin.readline())
nums = [int(sys.stdin.readline()) for _ in range(N)]
nums.sort()

print(round(sum(nums) / N))
print(nums[int(N / 2)])
counts = collections.Counter(nums)
arr = sorted([count for count in counts if counts[count] == max(counts.values())])
if len(arr) == 1:
    print(arr[0])
else:
    print(arr[1])
print(nums[-1] - nums[0])
