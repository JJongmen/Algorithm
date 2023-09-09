import sys

n = int(sys.stdin.readline())
abilities = list(map(int, sys.stdin.readline().rstrip().split()))

abilities.sort()
result = float('inf')
for i in range(n):
    result = min(result, abilities[i] + abilities[-(i + 1)])
print(result)