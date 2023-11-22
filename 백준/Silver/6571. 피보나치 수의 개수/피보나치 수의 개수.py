import sys
import bisect

input = sys.stdin.readline

fib = [0] * 501
fib[1] = 1
fib[2] = 2
for i in range(3, 501):
    fib[i] = fib[i - 1] + fib[i - 2]

while True:
    a, b = map(int, input().rstrip().split())
    if a == 0 and b == 0: break
    if a == 0: a = 1
    print(bisect.bisect_right(fib, b) - bisect.bisect_left(fib, a))