import sys
import bisect

T = int(sys.stdin.readline())
for _ in range(T):
    N, M = map(int, sys.stdin.readline().rstrip().split())
    A = list(map(int, sys.stdin.readline().rstrip().split()))
    B = list(map(int, sys.stdin.readline().rstrip().split()))
    A.sort()
    cnt = 0
    for num in B:
        cnt += N - bisect.bisect_right(A, num)
    print(cnt)
