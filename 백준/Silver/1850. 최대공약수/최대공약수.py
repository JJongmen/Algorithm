import sys

A, B = map(int, sys.stdin.readline().rstrip().split())

def gcd(x, y):
    if y == 0:
        return x
    return gcd(y, x % y)

print('1' * gcd(A, B))