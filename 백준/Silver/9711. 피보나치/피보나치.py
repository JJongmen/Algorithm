import sys

input = sys.stdin.readline

fib = [0] * 10001
fib[1] = fib[2] = 1
for i in range(3, 10001):
    fib[i] = fib[i - 1] + fib[i - 2]

T = int(input())
for i in range(1, T + 1):
    P, Q = map(int, input().rstrip().split())
    print(f'Case #{i}: {fib[P] % Q}')