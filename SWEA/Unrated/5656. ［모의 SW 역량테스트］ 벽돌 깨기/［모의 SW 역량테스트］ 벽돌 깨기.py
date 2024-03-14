# sys.stdin = open("input.txt", "r")

T = int(input())

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
def remove_blocks(col):
    que = []
    for i in range(H):
        if board[i][col] > 0:
            que.append((i, col, board[i][col] - 1))
            board[i][col] = 0
            break
    while que:
        x, y, k = que.pop(0)
        for d in range(4):
            for i in range(1, k + 1):
                nx = x + i * dx[d]
                ny = y + i * dy[d]
                if nx < 0 or nx >= H or ny < 0 or ny >= W: continue
                if board[nx][ny] == 0: continue
                que.append((nx, ny, board[nx][ny] - 1))
                board[nx][ny] = 0



def arrange_board():
    for j in range(W):
        col = []
        for i in range(H - 1, -1, -1):
            if board[i][j] > 0:
                col.append(board[i][j])
            board[i][j] = 0
        idx = H - 1
        while col:
            board[idx][j] = col.pop(0)
            idx -= 1



def drop_ball(remain_count):
    global board
    if remain_count == 0:
        global answer
        count = 0
        for i in range(H):
            for j in range(W):
                if board[i][j] > 0: count += 1
        answer = min(answer, count)
        return
    original_board = [row[:] for row in board]
    for i in range(W):
        remove_blocks(i)
        arrange_board()
        drop_ball(remain_count - 1)
        board = [row[:] for row in original_board]


for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    N, W, H = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(H)]
    answer = 1000
    drop_ball(N)
    print(f"#{test_case} {answer}")
    # ///////////////////////////////////////////////////////////////////////////////////
