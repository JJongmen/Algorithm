import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
board = [input().rstrip() for _ in range(N)]

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)

def dfs(x, y, sx, sy, cnt):
    visit[x][y] = True
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if nx<0 or nx>=N or ny<0 or ny>=M: continue
        if board[nx][ny] != board[sx][sy]: continue
        if cnt >= 4 and nx == sx and ny == sy:
            print('Yes')
            exit(0)
        if visit[nx][ny]: continue
        dfs(nx, ny, sx, sy, cnt + 1)
    visit[x][y] = False

visit = [[False] * M for _ in range(N)]

for i in range(N):
    for j in range(M):
        dfs(i, j, i, j, 1)
print('No')