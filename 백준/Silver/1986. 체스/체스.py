import sys

# 입력
n, m = map(int, sys.stdin.readline().rstrip().split())
queens = list(map(int, sys.stdin.readline().rstrip().split()))
knights = list(map(int, sys.stdin.readline().rstrip().split()))
pawns = list(map(int, sys.stdin.readline().rstrip().split()))

# 입력 데이터 전처리
Q, queens = queens[0], [(queens[i], queens[i + 1]) for i in range(1, queens[0] * 2, 2)]
K, knights = knights[0], [(knights[i], knights[i + 1]) for i in range(1, knights[0] * 2, 2)]
P, pawns = pawns[0], [(pawns[i], pawns[i + 1]) for i in range(1, pawns[0] * 2, 2)]

board = [[0] * (m + 1) for _ in range(n + 1)]  # -1 : 안전 X, 0 : 안전, 1 : 기물 존재
for queen in queens:
    board[queen[0]][queen[1]] = 1
for knight in knights:
    board[knight[0]][knight[1]] = 1
for pawn in pawns:
    board[pawn[0]][pawn[1]] = 1

# Queen
dx = (-1, 0, 1, 1, 1, 0, -1, -1)
dy = (1, 1, 1, 0, -1, -1, -1, 0)
for queen in queens:
    for d in range(8):
        nx = queen[0] + dx[d]
        ny = queen[1] + dy[d]
        while 1 <= nx <= n and 1 <= ny <= m and board[nx][ny] != 1:
            board[nx][ny] = -1
            nx += dx[d]
            ny += dy[d]

# Knight
dx = (-2, -1, 1, 2, 2, 1, -1, -2)
dy = (1, 2, 2, 1, -1, -2, -2, -1)
for knight in knights:
    for d in range(8):
        nx = knight[0] + dx[d]
        ny = knight[1] + dy[d]
        if nx < 1 or nx > n or ny < 1 or ny > m:
            continue
        if board[nx][ny] == 1:
            continue
        board[nx][ny] = -1

result = sum(board[i][j] == 0 for i in range(1, n + 1) for j in range(1, m + 1))
print(result)