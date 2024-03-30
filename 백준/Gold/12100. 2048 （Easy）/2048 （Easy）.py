from itertools import product

# 입력
N = int(input())
init_board = [list(map(int, input().split())) for _ in range(N)]

# 5번 이동할 방향의 경우의 수
cases = list(product(range(4), repeat=5))


def U():
    for j in range(N):
        tmp = []
        for i in range(N - 1, -1, -1):
            if board[i][j] == 0: continue
            tmp.append(board[i][j])
        idx = 0
        while len(tmp) >= 2:
            a, b = tmp.pop(), tmp[-1]
            if a == b:
                board[idx][j] = a * 2
                tmp.pop()
            else:
                board[idx][j] = a
            idx += 1
        if tmp:
            board[idx][j] = tmp[-1]
            idx += 1
        for i in range(idx, N):
            board[i][j] = 0


def D():
    for j in range(N):
        tmp = []
        for i in range(N):
            if board[i][j] == 0: continue
            tmp.append(board[i][j])
        idx = N - 1
        while len(tmp) >= 2:
            a, b = tmp.pop(), tmp[-1]
            if a == b:
                board[idx][j] = a * 2
                tmp.pop()
            else:
                board[idx][j] = a
            idx -= 1
        if tmp:
            board[idx][j] = tmp[-1]
            idx -= 1
        for i in range(idx, -1, -1):
            board[i][j] = 0


def R():
    for i in range(N):
        tmp = [num for num in board[i] if num > 0]
        idx = N - 1
        while len(tmp) >= 2:
            a, b = tmp.pop(), tmp[-1]
            if a == b:
                board[i][idx] = a * 2
                tmp.pop()
            else:
                board[i][idx] = a
            idx -= 1
        if tmp:
            board[i][idx] = tmp[-1]
            idx -= 1
        for j in range(idx, -1, -1):
            board[i][j] = 0


def L():
    for i in range(N):
        tmp = [num for num in board[i][::-1] if num > 0]
        idx = 0
        while len(tmp) >= 2:
            a, b = tmp.pop(), tmp[-1]
            if a == b:
                board[i][idx] = a * 2
                tmp.pop()
            else:
                board[i][idx] = a
            idx += 1
        if tmp:
            board[i][idx] = tmp[-1]
            idx += 1
        for j in range(idx, N):
            board[i][j] = 0


move = {0: U, 1: D, 2: L, 3: R}

answer = 0
for case in cases:
    board = [row[:] for row in init_board]
    # 1. 이동 및 합치기
    for d in case:
        move[d]()
    # 2. 최댓값 계산
    answer = max(answer, max(map(max,  board)))
print(answer)
