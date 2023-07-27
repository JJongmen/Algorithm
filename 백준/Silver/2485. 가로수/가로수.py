import sys

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

N = int(sys.stdin.readline())
positions = [int(sys.stdin.readline()) for _ in range(N)]

positions.sort()
dists = [positions[i + 1] - positions[i] for i in range(N - 1)]

gcd_val = dists[0]
for i in range(1, N - 1):
    gcd_val = gcd(gcd_val, dists[i])

ans = 0
for dist in dists:
    ans += dist // gcd_val - 1
print(ans)