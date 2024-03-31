# 입력
N = int(input())
K = int(input())
apples = [list(map(lambda x: int(x) - 1, input().split())) for _ in range(K)]
L = int(input())
rotates = {int(x[0]): x[1] for x in (input().split() for _ in range(L))}

# 초기화
board = [[[-1, False] for _ in range(N)] for _ in range(N)] # (방문 시간, 사과 존재 유무)
for i, j in apples:
    board[i][j][1] = True
board[0][0][0] = 0
x, y = 0, 0 # 현재 위치
time = 1 # 현재 시간
# 우, 하, 좌, 상 (우회전)
dx = (0, 1, 0, -1)
dy = (1, 0, -1, 0)
d = 0 # 방향 (초기 : 우)
size = 1 # 현재 몸 길이

while True:
    # 1. 이동
    nx, ny = x + dx[d], y + dy[d]
    # 벽에 부딪힐 시 종료
    if nx < 0 or nx >= N or ny < 0 or ny >= N: break
    # 몸에 부딪힐 시 종료
    if board[nx][ny][0] != -1 and time - board[nx][ny][0] <= size: break
    # 보드 갱신
    board[nx][ny][0] = time
    # 사과 있다면 몸 길이 증가
    if board[nx][ny][1]:
        board[nx][ny][1] = False
        size += 1
    x, y = nx, ny

    # 2. 방향 전환
    if time in rotates:
        if rotates[time] == 'D':
            d = (d + 1) % 4
        elif rotates[time] == 'L':
            d = (d + 3) % 4

    # 3. 시간 추가
    time += 1
print(time)