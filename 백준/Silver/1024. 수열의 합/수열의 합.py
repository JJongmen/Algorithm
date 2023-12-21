import sys

input = sys.stdin.readline

N, L = map(int, input().rstrip().split())

for i in range(L, 101):
    a = N / i + (1 - i) / 2
    if a >= 0 and float(a).is_integer():
        a = int(a)
        print(*range(a, a + i))
        exit()
print(-1)