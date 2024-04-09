N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

cctvs = [(x, y, board[x][y]) for x in range(N) for y in range(M) if board[x][y] > 0 and board[x][y] != 6]
dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1) # 북, 남, 서, 동
rotates = {
    1: [(0,), (1,), (2,), (3,)],
    2: [(0, 1), (2, 3)],
    3: [(0, 3), (1, 3), (1, 2), (0, 2)],
    4: [(0, 1, 2), (0, 1, 3), (0, 2, 3), (1, 2, 3)],
    5: [(0, 1, 2, 3)]
}

answer = len([0 for i in range(N) for j in range(M) if board[i][j] == 0])
def dfs(cctv_idx, board):
    if cctv_idx == len(cctvs):
        global answer
        answer = min(answer, len([0 for i in range(N) for j in range(M) if board[i][j] == 0]))
        return
    x, y, num = cctvs[cctv_idx]
    for directions in rotates[num]:
        tmp_board = [row[:] for row in board]
        for d in directions:
            nx, ny = x, y
            while 0 <= nx < N and 0 <= ny < M and tmp_board[nx][ny] < 6:
                tmp_board[nx][ny] = -1
                nx += dx[d]
                ny += dy[d]
        dfs(cctv_idx + 1, tmp_board)
dfs(0, board)
print(answer)