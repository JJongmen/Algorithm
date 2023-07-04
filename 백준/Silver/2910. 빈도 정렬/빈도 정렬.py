import sys

N, C = map(int, sys.stdin.readline().rstrip().split())
arr = list(map(int, sys.stdin.readline().rstrip().split()))
print(*sorted(arr, key=lambda x: (-arr.count(x), arr.index(x))))
