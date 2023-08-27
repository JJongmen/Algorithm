import sys

T = int(sys.stdin.readline())
for _ in range(T):
    n, k, t, m = map(int, sys.stdin.readline().rstrip().split())
    teams = [[0] * 3 for _ in range(n)]     # 최종 점수, 풀이 제출 횟수, 마지막 제출 시간
    scores = [[0] * k for _ in range(n)]    # 팀 별 문제 획득 점수
    for time in range(m):
        i, j, s = map(int, sys.stdin.readline().rstrip().split())
        scores[i - 1][j - 1] = max(scores[i - 1][j - 1], s)
        teams[i - 1][1] += 1
        teams[i - 1][2] = time

    for i in range(n):
        teams[i][0] = sum(scores[i])
    teams = sorted(enumerate(teams), key=lambda x: (-x[1][0], x[1][1], x[1][2]))

    for i in range(n):
        if teams[i][0] == t - 1:
            print(i + 1)
            break