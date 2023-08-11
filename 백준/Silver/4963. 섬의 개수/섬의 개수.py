import sys

dx = (-1, -1, -1, 0, 1, 1, 1, 0)
dy = (-1, 0, 1, 1, 1, 0, -1, -1)

sys.setrecursionlimit(10**6)

def dfs(x, y):
    visit[x][y] = True
    for d in range(8):
        nx = x + dx[d]
        ny = y + dy[d]
        if nx < 0 or nx >= h or ny < 0 or ny >= w: continue
        if board[nx][ny] == 0 or visit[nx][ny]: continue
        dfs(nx, ny)


while True:
    w, h = map(int, sys.stdin.readline().rstrip().split())
    if w == 0 and h == 0: exit()
    board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(h)]
    visit = [[False] * w for _ in range(h)]
    count = 0
    for i in range(h):
        for j in range(w):
            if board[i][j] == 0 or visit[i][j]: continue
            dfs(i, j)
            count += 1
    print(count)

