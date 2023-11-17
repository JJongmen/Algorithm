import sys

input = sys.stdin.readline

N, L = map(int, input().rstrip().split())
heights = list(map(int, input().rstrip().split()))

heights.sort()
for height in heights:
    if L >= height:
        L += 1
    else:
        break
print(L)