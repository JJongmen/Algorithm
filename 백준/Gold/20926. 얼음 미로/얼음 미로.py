import sys, heapq

W, H = map(int, sys.stdin.readline().rstrip().split())
board = []
for i in range(H):
    row = list(sys.stdin.readline().rstrip())
    for j in range(W):
        if row[j] == 'T':
            start = (i, j)
            row[j] = '0'
        elif row[j] == 'E':
            end = (i, j)
    board.append(row)    

def isInBoard(x, y):
    return x >= 0 and x < H and y >= 0 and y < W

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)

times = [[float('inf')] * W for _ in range(H)]
times[start[0]][start[1]] = 0
queue = []
heapq.heappush(queue, (0, start[0], start[1]))
while queue:
    t, x, y = heapq.heappop(queue)
    if times[x][y] < t: continue
    for d in range(4):
        nx, ny, nt = x, y, t
        while True:
            if not isInBoard(nx+dx[d], ny+dy[d]) or not board[nx+dx[d]][ny+dy[d]].isdigit(): break
            nx += dx[d]
            ny += dy[d]
            nt += int(board[nx][ny])
        if not isInBoard(nx + dx[d], ny + dy[d]) or board[nx+dx[d]][ny+dy[d]] == 'H': continue
        if board[nx+dx[d]][ny+dy[d]] == 'E':
            nx += dx[d]
            ny += dy[d]
        if times[nx][ny] > nt:
            times[nx][ny] = nt
            heapq.heappush(queue, (nt, nx, ny))

if times[end[0]][end[1]] != float('inf'):
    print(times[end[0]][end[1]])
else:                
    print(-1)