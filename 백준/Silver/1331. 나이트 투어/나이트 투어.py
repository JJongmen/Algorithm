import sys

route = []
for _ in range(36):
    point = sys.stdin.readline().rstrip()
    route.append((ord(point[0]) - ord('A'), ord(point[1]) - ord('1')))

visit = [[False] * 6 for _ in range(6)]
prev = None
for point in route:
    if visit[point[0]][point[1]]:
        print("Invalid")
        exit(0)
    if prev:
        diffX = abs(prev[0] - point[0])
        diffY = abs(prev[1] - point[1])
        if diffX + diffY != 3 or diffX == 0 or diffY == 0:
            print("Invalid")
            exit(0)
    visit[point[0]][point[1]] = True
    prev = point
# 마지막 칸에서 첫 칸으로 돌아올 수 있는지 확인
diffX = abs(route[-1][0] - route[0][0])
diffY = abs(route[-1][1] - route[0][1])
if diffX + diffY != 3 or diffX == 0 or diffY == 0:
    print("Invalid")
    exit(0)
print("Valid")