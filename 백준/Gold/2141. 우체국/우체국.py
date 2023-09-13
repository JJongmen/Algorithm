import sys

N = int(sys.stdin.readline())
villages = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

total_population = sum([village[1] for village in villages])
villages.sort(key=lambda x: x[0])
cur_population = 0
for i in range(N):
    cur_population += villages[i][1]
    if cur_population >= total_population / 2:
        print(villages[i][0])
        break