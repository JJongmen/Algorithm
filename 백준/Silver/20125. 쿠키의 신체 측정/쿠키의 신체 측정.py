import sys

N = int(sys.stdin.readline())
board = [sys.stdin.readline().rstrip() for _ in range(N)]

# 심장 찾기
heart = None
for i in range(N):
    for j in range(N):
        if board[i][j] == '*' and not heart:
            heart = (i + 1, j)
            break

# 왼, 오, 아래
dx = (0, 0, 1)
dy = (-1, 1, 0)

def getLength(x, y, d):
    length = 0
    while True:
        x += dx[d]
        y += dy[d]
        if x < 0 or x >= N or y < 0 or y >= N:
            break
        if board[x][y] == '*':
            length += 1
        else:
            break
    return length

leftArm = getLength(heart[0], heart[1], 0)
rightArm = getLength(heart[0], heart[1], 1)
waist = getLength(heart[0], heart[1], 2)
leftLeg = getLength(heart[0] + waist, heart[1] - 1, 2)
rightLeg = getLength(heart[0] + waist, heart[1] + 1, 2)

print(heart[0] + 1, heart[1] + 1)
print(leftArm, rightArm, waist, leftLeg, rightLeg)