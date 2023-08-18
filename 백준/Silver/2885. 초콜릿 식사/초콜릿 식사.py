import math
import sys

K = int(sys.stdin.readline())
cnt = math.ceil(math.log2(K))
for i in range(0, cnt + 1):
    if K & (1 << i) > 0:
        print(2 ** cnt, cnt - i)
        exit()

