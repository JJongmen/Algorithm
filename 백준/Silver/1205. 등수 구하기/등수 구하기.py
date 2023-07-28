import sys
import bisect

N, new, P = map(int, sys.stdin.readline().rstrip().split())
if N == 0:
    print(1)
    exit()
scores = list(map(int, sys.stdin.readline().rstrip().split()))

if N == P and new <= scores[-1]:
    print(-1)
    exit()
scores.reverse()
print(N + 1 - bisect.bisect_right(scores, new))