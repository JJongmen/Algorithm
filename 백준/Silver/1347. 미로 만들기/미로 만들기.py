import sys

N = int(sys.stdin.readline())
note = sys.stdin.readline().rstrip()

dx = (-1, 0, 1, 0)
dy = (0, 1, 0, -1)
board = [[False] * 101 for _ in range(101)]
x, y, d = 50, 50, 2
board[x][y] = True
xMin, xMax, yMin, yMax = 50, 50, 50, 50
for action in note:
    if action == 'L':
        d = (d + 3) % 4
    elif action == 'R':
        d = (d + 1) % 4
    else:
        x += dx[d]
        y += dy[d]
    board[x][y] = True
    xMin = min(xMin, x)
    xMax = max(xMax, x)
    yMin = min(yMin, y)
    yMax = max(yMax, y)

for i in range(xMin, xMax + 1):
    row = []
    for j in range(yMin, yMax + 1):
        if board[i][j]:
            row.append('.')
        else:
            row.append('#')
    print(*row, sep='')