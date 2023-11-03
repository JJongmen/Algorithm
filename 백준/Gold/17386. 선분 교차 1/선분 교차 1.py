import sys
from math import gcd

input = sys.stdin.readline

x1, y1, x2, y2 = map(int, input().rstrip().split())
x3, y3, x4, y4 = map(int, input().rstrip().split())

A, B, C, D = (x1, y1), (x2, y2), (x3, y3), (x4, y4)

def ccw(a, b, c):
    return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0])

if ccw(A, B, C) * ccw(A, B, D) < 0 and ccw(C, D, A) * ccw(C, D, B) < 0:
    print(1)
else:
    print(0)