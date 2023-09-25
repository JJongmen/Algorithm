import sys

dx = (-1, -1, -1, 0, 0, 1, 1, 1)
dy = (-1, 0, 1, -1, 1, -1, 0, 1)

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

def bfs(x, y):
    visit = [[False] * M for _ in range(N)]
    visit[x][y] = True
    que = [(x, y, 0)]
    while que:
        x, y, dist = que.pop(0)
        for i in range(8):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            if visit[nx][ny]: continue
            if board[nx][ny] == 1: return dist + 1
            visit[nx][ny] = True
            que.append((nx, ny, dist + 1))
    return -1

result = 0
for i in range(N):
    for j in range(M):
        if board[i][j] == 1: continue
        result = max(result, bfs(i, j))
print(result)