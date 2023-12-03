import sys

input = sys.stdin.readline

N, A, D = map(int, input().rstrip().split())
nums = list(map(int, input().rstrip().split()))

result = 0
cur = A
for num in nums:
    if num == cur:
        result += 1
        cur += D
print(result)