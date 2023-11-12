import sys
import bisect

input = sys.stdin.readline

L = int(input())
S = list(map(int, input().rstrip().split()))
n = int(input())

if n in S:
    print(0)
    exit(0)

S.sort()
idx = bisect.bisect_left(S, n)
left = S[idx - 1] if idx > 0 else 0
right = S[idx]
print((n - left) * (right - n) - 1)