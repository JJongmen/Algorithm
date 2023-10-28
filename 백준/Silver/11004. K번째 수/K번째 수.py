import sys

input = sys.stdin.readline
N, K = map(int, input().rstrip().split())
A = list(map(int, input().rstrip().split()))
A.sort()
print(A[K - 1])