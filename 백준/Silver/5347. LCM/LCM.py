import sys

def gcd(x, y):
    if y == 0:
        return x
    return gcd(y, x % y)

n = int(sys.stdin.readline())
for _ in range(n):
    a, b = map(int, sys.stdin.readline().split())
    print(int(a * b / gcd(a, b)))