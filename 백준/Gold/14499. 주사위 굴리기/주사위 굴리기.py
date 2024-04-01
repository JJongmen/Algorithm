global x, y
N, M, x, y, K = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
directions = list(map(int, input().split()))

# (X, 동, 서, 북, 남)
dx = (0, 0, 0, -1, 1)
dy = (0, 1, -1, 0, 0)
dice = [[0] * 4 for _ in range(4)]

def R():
    tmp = dice[1][3]
    for i in range(3, 0, -1):
        dice[1][i] = dice[1][i - 1]
    dice[1][0] = tmp
    dice[3][1] = dice[1][3]

def L():
    tmp = dice[1][0]
    for i in range(3):
        dice[1][i] = dice[1][i + 1]
    dice[3][1] = dice[1][3] = tmp

def U():
    tmp = dice[0][1]
    for i in range(3):
        dice[i][1] = dice[i + 1][1]
    dice[1][3] = dice[3][1] = tmp

def D():
    tmp = dice[3][1]
    for i in range(3, 0, -1):
        dice[i][1] = dice[i - 1][1]
    dice[0][1] = tmp
    dice[1][3] = dice[3][1]

roll = {1:R, 2:L, 3:U, 4:D}

for d in directions:
    nx = x + dx[d]
    ny = y + dy[d]
    # 지도의 바깥으로 이동시키려고 하는 경우 명령 무시
    if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
    x, y= nx, ny
    
    roll[d]()

    # 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
    if board[x][y] == 0:
        board[x][y] = dice[3][1]
    # 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
    else:
        dice[3][1] = dice[1][3] = board[x][y]
        board[x][y] = 0
    print(dice[1][1])