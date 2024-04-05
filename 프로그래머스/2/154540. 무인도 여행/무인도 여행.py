import sys 
sys.setrecursionlimit(10000)

def solution(maps):
    answer = []
    N, M = len(maps), len(maps[0])
    visit = [[False] * M for _ in range(N)]
    dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1)
    def dfs(x, y):
        visit[x][y] = True
        sum_ = int(maps[x][y])
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            if maps[nx][ny] == 'X': continue
            if visit[nx][ny]: continue
            sum_ += dfs(nx, ny)
        return sum_
    for i in range(N):
        for j in range(M):
            if maps[i][j] == 'X': continue
            if visit[i][j]: continue
            answer.append(dfs(i, j))
    if not answer:
        return [-1]
    return sorted(answer)