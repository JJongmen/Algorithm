import sys

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

input = sys.stdin.readline

D1, D2 = map(int, input().rstrip().split())

memo = [[False] * 2001 for _ in range(2001)]
cnt = 0
for i in range(D1, D2 + 1):
    for j in range(1, i + 1):
        gcd_ = gcd(i, j)
        if memo[i // gcd_][j // gcd_]: continue
        memo[i // gcd_][j // gcd_] = True
        cnt += 1
print(cnt)