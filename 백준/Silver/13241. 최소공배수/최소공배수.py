import sys
from math import lcm

input = sys.stdin.readline

A, B = map(int, input().rstrip().split())
print(lcm(A, B))