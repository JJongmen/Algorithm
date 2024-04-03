from itertools import combinations

N, M = map(int, input().split())
init_board = [list(map(int, input().split())) for _ in range(N)]

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)
viruses = [(x, y) for x in range(N) for y in range(M) if init_board[x][y] == 2]
walls = [(x, y) for x in range(N) for y in range(M) if init_board[x][y] == 0]
walls = list(combinations(walls, 3))
answer = 0

for wall in walls:
    board = [row[:] for row in init_board]
    for x, y in wall:
        board[x][y] = 1
    def dfs(x, y):
        board[x][y] = 2
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            if board[nx][ny] != 0: continue
            dfs(nx, ny)

    [dfs(x, y) for x, y in viruses]
    answer = max(answer, len([1 for x in range(N) for y in range(M) if board[x][y] == 0]))
print(answer)