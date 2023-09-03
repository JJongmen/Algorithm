import sys

H, W, N, M = map(int, sys.stdin.readline().rstrip().split())
print((1 + (H - 1) // (N + 1) ) * (1 + (W - 1) // (M + 1)))