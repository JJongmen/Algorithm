import sys

N, X = map(int, sys.stdin.readline().rstrip().split())
visitors = list(map(int, sys.stdin.readline().rstrip().split()))


cnt = 0
for i in range(X):
    cnt += visitors[i]
__max = cnt
period = 1

for i in range(X, N):
    cnt = cnt + visitors[i] - visitors[i - X]
    if __max < cnt:
        __max = cnt
        period = 1
    elif __max == cnt:
        period += 1

if __max == 0:
    print("SAD")
else:
    print(__max)
    print(period)
