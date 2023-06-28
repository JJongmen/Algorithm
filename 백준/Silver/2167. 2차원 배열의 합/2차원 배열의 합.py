N, M = map(int, input().split())

board = [[0 for j in range(M + 1)] for i in range(N + 1)]
for i in range(1, N + 1):
    tokens = list(map(int, input().split()))
    for j in range(1, M + 1):
        board[i][j] = tokens[j - 1]
        board[i][j] += board[i][j - 1]

K = int(input())
for _ in range(K):
    i, j, x, y = map(int, input().split())
    res = 0
    for l in range(i, x + 1):
        res += board[l][y] - board[l][j - 1]
    print(res)