import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [list(map(int,''.join(sys.stdin.readline().rstrip()))) for _ in range(N)]

len = min(N, M)
for l in range(len, 0, -1):
    for i in range(N - l + 1):
        for j in range(M - l + 1):
            if board[i][j] == board[i + l - 1][j] == board[i][j + l - 1] == board[i + l - 1][j + l - 1]:
                print(l ** 2)
                exit(0)