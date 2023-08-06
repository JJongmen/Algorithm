import sys

sys.setrecursionlimit(10**6)

R, C = map(int, sys.stdin.readline().rstrip().split())
mapping = dict(zip(['#', '.', 'o', 'v'], range(-1, 3)))
board = [[mapping[ch] for ch in sys.stdin.readline().rstrip()] for _ in range(R)]
visit = [[False] * C for _ in range(R)]

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)

def dfs(x, y, sheep, wolf):
    visit[x][y] = True
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if nx < 0 or nx >= R or ny < 0 or ny >= C: continue
        if visit[nx][ny] or board[nx][ny] == -1: continue
        if board[nx][ny] == 1: sheep += 1
        if board[nx][ny] == 2: wolf += 1
        sheep, wolf = dfs(nx, ny, sheep, wolf)
    return sheep, wolf


total_sheep, total_wolf = 0, 0
for i in range(R):
    for j in range(C):
        if visit[i][j]: continue
        sheep, wolf = dfs(i, j, 0, 0)
        if sheep > wolf: total_sheep += sheep
        else: total_wolf += wolf
print(total_sheep, total_wolf)