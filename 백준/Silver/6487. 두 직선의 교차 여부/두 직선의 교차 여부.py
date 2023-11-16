import sys

input = sys.stdin.readline

N = int(input())
for _ in range(N):
    x1, y1, x2, y2, x3, y3, x4, y4 = map(int, input().rstrip().split())
    dx1, dy1 = x2 - x1, y2 - y1
    dx2, dy2 = x4 - x3, y4 - y3
    # 두 직선 모두 수직인 경우
    if dx1 == 0 and dx2 == 0:
        # 일치
        if x1 == x3:
            print("LINE")
        else:
            print("NONE")
        continue
    # 하나의 직선만 수직인 경우
    if dx1 == 0:
        d2 = dy2 / dx2
        x = x1
        y = d2 * (x - x3) + y3
        print(f"POINT {x:.2f} {y:.2f}")
        continue
    if dx2 == 0:
        d1 = dy1 / dx1
        x = x3
        y = d1 * (x - x1) + y1
        print(f"POINT {x:.2f} {y:.2f}")
        continue
    d1, d2 = dy1 / dx1, dy2 / dx2
    # 두 직선의 기울기가 같은 경우
    if d1 == d2:
        if y3 == d1 * (x3 - x1) + y1:
            print("LINE")
        else:
            print("NONE")
        continue
    # 두 직선이 교차하는 경우
    x = (d1 * x1 - d2 * x3 + y3 - y1) / (d1 - d2)
    y = d1 * (x - x1) + y1
    print(f"POINT {x:.2f} {y:.2f}")
