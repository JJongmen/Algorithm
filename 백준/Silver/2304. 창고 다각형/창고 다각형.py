import sys

N = int(sys.stdin.readline())
points = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
# if N == 1:
#     print(points[0][1])
#     exit(0)
points.sort()

max_x, max_y = max(points, key=lambda x: x[1])


def getIncreaseArea():
    result = 0
    prev_x, prev_y = points[0]
    for i in range(N):
        if points[i][0] == max_x:
            result += abs(points[i][0] - prev_x) * prev_y
            break
        if points[i][1] < prev_y: continue
        result += abs(points[i][0] - prev_x) * prev_y
        prev_x, prev_y = points[i]
    return result

result = 0
result += getIncreaseArea()
points.reverse()
result += getIncreaseArea()
result += max_y

print(result)