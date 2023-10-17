import sys

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().rstrip().split()))

A.sort()
print(*A)