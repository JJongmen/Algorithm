import sys

input = sys.stdin.readline

R, C = map(int, input().rstrip().split())
board = [input().rstrip() for _ in range(R)]

words = []
for i in range(R):
    word = ''
    for j in range(C):
        if board[i][j] == '#':
            if len(word) > 1:
                words.append(word)
            word = ''
        else:
            word += board[i][j]
    if len(word) > 1:
        words.append(word)

for j in range(C):
    word = ''
    for i in range(R):
        if board[i][j] == '#':
            if len(word) > 1:
                words.append(word)
            word = ''
        else:
            word += board[i][j]
    if len(word) > 1:
        words.append(word)

words.sort()
print(words[0])