import sys

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    x1, y1, x2, y2 = map(int, input().rstrip().split())
    n = int(input())
    cnt = 0
    for _ in range(n):
        cx, cy, r = map(int, input().rstrip().split())
        # (x - cx) ^ 2 + (y - cy) ^ 2 <= r ^ 2
        if (x1 - cx) ** 2 + (y1 - cy) ** 2 < r ** 2:
            if (x2 - cx) ** 2 + (y2 - cy) ** 2 >= r ** 2:
                cnt += 1
        else:
            if (x2 - cx) ** 2 + (y2 - cy) ** 2 < r ** 2:
                cnt += 1
    print(cnt)