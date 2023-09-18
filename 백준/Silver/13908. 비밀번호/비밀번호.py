import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
if M == 0:
    print(10 ** N)
    exit(0)
knows = set(map(int, sys.stdin.readline().rstrip().split()))
nums = [-1] * 7
result = 0
def dfs(idx):
    global result
    if idx == N:
        for num in knows:
            if not num in nums: return
        result += 1
        return
    for i in range(10):
        nums[idx] = i
        dfs(idx + 1)

dfs(0)
print(result)