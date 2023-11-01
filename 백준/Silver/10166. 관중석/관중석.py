import sys
from math import gcd

def solve():
    input = sys.stdin.readline

    D1, D2 = map(int, input().rstrip().split())

    memo = [[False] * 2001 for _ in range(2001)]
    cnt = 0
    for i in range(D1, D2 + 1):
        for j in range(1, i + 1):
            gcd_ = gcd(i, j)
            x, y = i // gcd_, j // gcd_
            if memo[x][y]: continue
            memo[x][y] = True
            cnt += 1
    return cnt


print(solve())