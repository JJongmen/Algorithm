import sys

tokens = sys.stdin.readline().rstrip().split()
n = int(tokens[0])
nums = list(map(lambda x : int(x[::-1]), tokens[1:]))
while len(nums) < n:
    nums.extend(map(lambda x : int(x[::-1]), sys.stdin.readline().rstrip().split()))
print(*sorted(nums), sep='\n')