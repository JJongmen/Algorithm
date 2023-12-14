import sys

input = sys.stdin.readline

xa, ya, xb, yb, xc, yc = map(int, input().rstrip().split())
if (yc - ya) * (xb - xa) == (yb - ya) * (xc - xa):
    print(-1)
    exit()

ab = ((xa - xb) ** 2 + (ya - yb) ** 2) ** 0.5
bc = ((xb - xc) ** 2 + (yb - yc) ** 2) ** 0.5
ca = ((xc - xa) ** 2 + (yc - ya) ** 2) ** 0.5

sum_ = ab + bc + ca
max_ = 2 * (sum_ - min(ab, bc, ca))
min_ = 2 * (sum_ - max(ab, bc, ca))
print(max_ - min_)