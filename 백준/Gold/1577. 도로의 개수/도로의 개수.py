import sys
from math import comb

def solve():
    input = sys.stdin.readline

    N, M = map(int, input().rstrip().split())
    K = int(input())
    # can_go[i][j][0]: (i, j) -> (i, j + 1)
    # can_go[i][j][1]: (i, j) -> (i + 1, j)
    can_go = [[[True] * 2 for _ in range(N + 1)] for _ in range(M + 1)]
    for _ in range(K):
        a, b, c, d = map(int, input().rstrip().split())
        if a == c:
            if b < d:
                can_go[b][a][1] = False
            else:
                can_go[d][c][1] = False
        else:
            if a < c:
                can_go[b][a][0] = False
            else:
                can_go[d][c][0] = False
    dp = [[0] * (N + 1) for _ in range(M + 1)]
    dp[0][0] = 1
    for i in range(M + 1):
        for j in range(N + 1):
            if j < N and can_go[i][j][0]:
                dp[i][j + 1] += dp[i][j]
            if i < M and can_go[i][j][1]:
                dp[i + 1][j] += dp[i][j]
    print(dp[M][N])

solve()