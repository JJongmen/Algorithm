import sys

N = int(sys.stdin.readline())
room = [sys.stdin.readline().rstrip() for _ in range(N)]

X, Y = 0, 0
for i in range(N):
    row = room[i].split('X')
    for token in row:
        if len(token) >= 2:
            X += 1
    col = ''.join([row[i] for row in room]).split('X')
    for token in col:
        if len(token) >= 2:
            Y += 1

print(X, Y)