import sys

N = int(sys.stdin.readline())
nums = [sys.stdin.readline().rstrip() for _ in range(N)]
print(*sorted(nums, key=lambda x : (len(x), sum([int(i) for i in x if i.isdigit()]), x)), sep='\n')