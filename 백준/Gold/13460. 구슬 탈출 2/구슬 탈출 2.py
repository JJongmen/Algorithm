from collections import deque

N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
rx, ry, bx, by = 0, 0, 0, 0
for i in range(N):
    for j, k in enumerate(board[i]):
        if k == 'R':
            rx, ry = i, j
            board[i][j] = '.'
        elif k == 'B':
            bx, by = i, j
            board[i][j] = '.'

q = deque([(rx, ry, bx, by, 0)])
dx, dy = (-1, 1, 0, 0), (0, 0, -1, 1) # 상, 하, 좌, 우
while q:
    rx, ry, bx, by, cnt = q.popleft()
    if cnt >= 10: break
    for d in range(4):
        nrx, nry, nbx, nby = rx, ry, bx, by
        while board[nrx][nry] == '.':
            nrx += dx[d]
            nry += dy[d]
        while board[nbx][nby] == '.':
            nbx += dx[d]
            nby += dy[d]
        if board[nrx][nry] == '#':
            nrx -= dx[d]
            nry -= dy[d]
        if board[nbx][nby] == '#':
            nbx -= dx[d]
            nby -= dy[d]
        # 구슬이 같은 위치에 있는지 체크
        if nrx == nbx and nry == nby:
            if board[nrx][nry] == 'O': continue # 두 구슬이 동시에 구멍에 빠지면 실패
            else: # 두 구슬이 같은 빈 칸에 있는 경우
                if d == 0: # 위로 기울인 경우
                    if rx < bx:
                        nbx += 1
                    else:
                        nrx += 1
                elif d == 1: # 아래로 기울인 경우
                    if rx < bx:
                        nrx -= 1
                    else:
                        nbx -= 1
                elif d == 2: # 왼쪽으로 기울인 경우
                    if ry < by:
                        nby += 1
                    else:
                        nry += 1
                elif d == 3: # 오른쪽으로 기울인 경우
                    if ry < by:
                        nry -= 1
                    else:
                        nby -= 1
        if board[nrx][nry] == 'O': # 빨간 구슬만 구멍에 들어간 경우 성공
            print(cnt + 1)
            exit()
        q.append((nrx, nry, nbx, nby, cnt + 1))
print(-1)