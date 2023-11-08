import sys

def solve():
    input = sys.stdin.readline

    N, M, K = map(int, input().rstrip().split())
    graph = [[0] * (N + 1) for _ in range(N + 1)]
    for _ in range(K):
        a, b, c = map(int, input().rstrip().split())
        if a >= b: continue
        graph[a][b] = max(graph[a][b], c)
    
    dp = [[0] * (M + 1) for _ in range(N + 1)]
    for i in range(2, N + 1):
        dp[i][2] = graph[1][i]
    for i in range(2, N + 1):
        for j in range(3, M + 1):
            for k in range(1, i):
                if dp[k][j - 1] and graph[k][i]:
                    dp[i][j] = max(dp[i][j], dp[k][j - 1] + graph[k][i])
    print(max(dp[N]))

solve()