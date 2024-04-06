N, M = map(int, input().split())
r, c, d = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dx, dy = (-1, 0, 1, 0), (0, 1, 0, -1)
clean_cnt = 0
while True:
    # 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다
    if board[r][c] == 0:
        board[r][c] = -1
        clean_cnt += 1
    need_clean = False
    for i in range(4):
        nx, ny = r + dx[i], c + dy[i]
        if board[nx][ny] == -1 or board[nx][ny] == 1: continue
        need_clean = True
        break
    # 주변 칸 중 청소되지 않은 빈 칸 존재
    if need_clean:
        d = (d + 3) % 4 # 반시계 방향 90도 회전
        nx, ny = r + dx[d], c + dy[d]
        # 바라보는 방향 기준 앞 칸이 청소되지 않았다면 한 칸 전진
        if board[nx][ny] == 0:
            r, c = nx, ny
    else:
        nx, ny = r - dx[d], c - dy[d]
        if board[nx][ny] == 1: break # 후진 불가하면 작동 멈춤
        r, c = nx, ny # 후진 가능하면 이동

print(clean_cnt)
