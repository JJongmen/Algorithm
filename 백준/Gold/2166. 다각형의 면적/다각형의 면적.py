import sys, math

N = int(sys.stdin.readline().rstrip())
points = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

points.append(points[0])

result = 0
result = sum([(points[i][0] + points[i + 1][0])*(points[i][1]-points[i+1][1]) for i in range(N)]) / 2
print(round(abs(result), 1))