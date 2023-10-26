import sys
from collections import deque

input = sys.stdin.readline

N, M, K = map(int, input().rstrip().split())
board = [[0] * M for _ in range(N)]
for _ in range(K):
    r, c = map(int, input().rstrip().split())
    board[r - 1][c - 1] = 1

def bfs(x, y):
    que = deque([(x, y)])
    visit[x][y] = True
    cnt = 1
    while que:
        x, y = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            if visit[nx][ny]: continue
            if board[nx][ny] == 0: continue
            que.append((nx, ny))
            visit[nx][ny] = True
            cnt += 1
    return cnt

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)
visit = [[False] * M for _ in range(N)]
result = 0
for x in range(N):
    for y in range(M):
        if visit[x][y]: continue
        if board[x][y] == 0: continue
        cnt = bfs(x, y)
        result = max(result, cnt)
print(result)