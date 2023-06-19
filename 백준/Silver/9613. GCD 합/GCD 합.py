import sys

def gcd(x, y):
    if y == 0:
        return x
    return gcd(y, x % y)

t = int(sys.stdin.readline())
for _ in range(t):
    st = list(map(int, sys.stdin.readline().rstrip().split()))
    n = st[0]
    sum = 0
    for i in range(1, n):
        for j in range(i + 1, n + 1):
           sum += gcd(st[i], st[j])
    print(sum)