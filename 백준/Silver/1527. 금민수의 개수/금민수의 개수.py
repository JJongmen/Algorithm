import sys
import bisect

nums = []

def dfs(idx, num):
    if idx == 10: return
    nums.append(num)
    dfs(idx + 1, num * 10 + 4)
    dfs(idx + 1, num * 10 + 7)

def init_금민수():
    dfs(1, 4)
    dfs(1, 7)
    return 0

A, B = map(int, sys.stdin.readline().rstrip().split())

init_금민수()
nums.sort()
print(bisect.bisect_right(nums, B) - bisect.bisect_left(nums, A))