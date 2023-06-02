import math
import sys

msg = sys.stdin.readline().rstrip()
length = len(msg)
for R in range(int(math.sqrt(length)), 0, -1):
    if length % R == 0:
        C = int(length / R)
        original = [msg[j * R + i] for i in range(R) for j in range(C)]
        print(''.join(original))
        exit()
