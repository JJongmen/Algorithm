N, M = map(int, input().split())
board = [[0 for _ in range(M + 1)]]
for i in range(1, N + 1):
    board.append([0])
    board[i].extend(list(map(int, input().split())))

for i in range(1, N + 1):
    for j in range(1, M + 1):
        board[i][j] += max(board[i - 1][j], board[i][j - 1], board[i - 1][j - 1])
print(board[N][M])