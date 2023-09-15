import sys

dx = [0, 1]
dy = [1, 0]
mapper = {'C': 0, 'P': 1, 'Z': 2, 'Y': 3}

N = int(sys.stdin.readline())
board = [sys.stdin.readline().rstrip() for _ in range(N)]
board = [[mapper[board[i][j]] for j in range(N)] for i in range(N)]

def getLongestLength(board):
    result = 0
    for i in range(N):
        cnt = 1
        for j in range(N - 1):
            if board[i][j] == board[i][j + 1]:
                cnt += 1
            else:
                cnt = 1
            result = max(result, cnt)
    for j in range(N):
        cnt = 1
        for i in range(N - 1):
            if board[i][j] == board[i + 1][j]:
                cnt += 1
            else:
                cnt = 1
            result = max(result, cnt)
    return result

result = 0
for i in range(N):
    for j in range(N):
        for k in range(2):
            nx = i + dx[k]
            ny = j + dy[k]
            if nx >= N or ny >= N: continue
            board[i][j], board[nx][ny] = board[nx][ny], board[i][j]
            result = max(result, getLongestLength(board))
            board[i][j], board[nx][ny] = board[nx][ny], board[i][j]
print(result)