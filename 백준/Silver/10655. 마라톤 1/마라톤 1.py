import sys

def get_dist(point1, point2):
    return abs(point1[0] - point2[0]) + abs(point1[1] - point2[1])

N = int(sys.stdin.readline())
checkpoints = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

total_dist = 0
for i in range(N - 1):
    total_dist += get_dist(checkpoints[i], checkpoints[i + 1])

exclude_dist = 0
for i in range(1, N - 1):
    dist = get_dist(checkpoints[i - 1], checkpoints[i + 1]) - get_dist(checkpoints[i - 1], checkpoints[i]) - get_dist(checkpoints[i], checkpoints[i + 1])
    exclude_dist = min(exclude_dist, dist)
print(total_dist + exclude_dist)

