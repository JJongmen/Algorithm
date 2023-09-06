import math
import sys

N, L = map(int, sys.stdin.readline().rstrip().split())
pools = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
pools.sort()
cnt, last = 0, 0
for pool in pools:
    start, end = pool
    if last < start:
        need_cnt = math.ceil((end - start) / L)
        cnt += need_cnt
        last = start - 1 + need_cnt * L
    elif last < end:
        need_cnt = math.ceil((end - 1 - last) / L)
        cnt += need_cnt
        last += need_cnt * L
print(cnt)