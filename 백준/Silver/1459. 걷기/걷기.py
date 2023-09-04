import sys

X, Y, W, S = map(int, sys.stdin.readline().rstrip().split())

max_ = max(X, Y)
min_ = min(X, Y)

if 2 * W < S:
    print((X + Y) * W)
elif W < S:
    print(min_ * S + (max_ - min_) * W)
else:
    result = min_ * S + ((max_ - min_) // 2) * 2 * S
    if (max_ - min_) % 2 == 1:
        result += W
    print(result)