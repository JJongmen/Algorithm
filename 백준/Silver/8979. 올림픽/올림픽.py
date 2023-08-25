import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
infos = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

infos.sort(key=lambda x: (-x[1], -x[2], -x[3]))
for i in range(N):
    if infos[i][0] == K:
        same = 0
        for j in range(i - 1, -1, -1):
            if infos[i][1:] == infos[j][1:]:
                same += 1
            else:
                break
        print(i + 1 - same)
        break