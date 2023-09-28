import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
board = [sys.stdin.readline().rstrip() for _ in range(M)]

visit = [[False] * N for _ in range(M)]
dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)

def dfs(x, y):
    que = [(x, y)]
    visit[x][y] = True
    team = board[x][y]
    cnt = 0
    while que:
        x, y = que.pop(0)
        cnt += 1
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= M or ny < 0 or ny >= N: continue
            if visit[nx][ny]: continue
            if board[nx][ny] != team: continue
            que.append((nx, ny))
            visit[nx][ny] = True
    return cnt

myTeam, enemyTeam = 0, 0
for i in range(M):
    for j in range(N):
        if visit[i][j]: continue
        if board[i][j] == 'W':
            myTeam += dfs(i, j) ** 2
        else:
            enemyTeam += dfs(i, j) ** 2
print(myTeam, enemyTeam)
