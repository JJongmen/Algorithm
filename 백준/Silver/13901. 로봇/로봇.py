import sys

input = sys.stdin.readline

R, C = map(int, input().rstrip().split())
board = [[0] * C for _ in range(R)]
k = int(input())
for _ in range(k):
    br, bc = map(int, input().rstrip().split())
    board[br][bc] = -1
sr, sc = map(int, input().rstrip().split())
directions = list(map(int, input().rstrip().split()))

dx = (0, -1, 1, 0, 0)
dy = (0, 0, 0, -1, 1)
d_idx = 0
x, y = sr, sc
board[x][y] = 1
cantMoveCnt = 0

def canMove(nx, ny):
    if nx < 0 or ny < 0 or nx >= R or ny >= C: return False
    if board[nx][ny] != 0: return False
    return True

while True:
    if cantMoveCnt == 4: break
    d = directions[d_idx]
    nx, ny = x + dx[d], y + dy[d]
    if canMove(nx, ny):
        x, y = nx, ny
        board[x][y] = 1
        cantMoveCnt = 0
    else:
        d_idx = (d_idx + 1) % 4
        cantMoveCnt += 1
print(x, y)