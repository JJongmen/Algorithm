N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

visit = [[False] * M for _ in range(N)]
dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)

global answer
answer = 0
def dfs(x, y, sum_, cnt):
    global answer
    if cnt == 4:
        answer = max(answer, sum_)
        return
    visit[x][y] = True
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
        if visit[nx][ny]: continue
        dfs(nx, ny, sum_ + board[nx][ny], cnt + 1)
    visit[x][y] = False

def another_shape(x, y):
    global answer
    sum_ = board[x][y]
    directions = []
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
        directions.append(d)
        sum_ += board[nx][ny]
    
    if len(directions) <= 2:
        return
    if len(directions) == 3:
        answer = max(answer, sum_)
        return
    for d in directions:
        answer = max(answer, sum_ - board[x + dx[d]][y + dy[d]])


for i in range(N):
    for j in range(M):
        dfs(i, j, board[i][j], 1)
        another_shape(i, j)
print(answer)