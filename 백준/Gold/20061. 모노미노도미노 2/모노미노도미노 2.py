def block_down(kind, idx, board):
    if kind == 1 or kind == 3:
        i = 0
        while i < 6 and board[i][idx] == 0:
            i += 1
        board[i - 1][idx] = kind
        if kind == 3:
            board[i - 2][idx] = kind
    else:
        i = 0
        while i < 6 and board[i][idx] == 0 and board[i][idx + 1] == 0:
            i += 1
        board[i - 1][idx] = kind
        board[i - 1][idx + 1] = kind
    return board

def removeIfNeed(board):
    i = 5
    while i >= 0:
        if len([x for x in board[i] if x != 0]) == 4:
            global score
            score += 1
            board = [[0] * 4] + board[:i] + board[i + 1:]
        else:
            i -= 1
    return board

def processLighter(board):
    if len([x for x in board[1] if x != 0]) == 0:
        return board
    if len([x for x in board[0] if x != 0]) == 0:
        return [[0] * 4 for _ in range(2)] + board[1:5]
    return [[0] * 4 for _ in range(2)] + board[:4]

N = int(input())
green_board = [[0] * 4 for _ in range(6)]
blue_board = [[0] * 4 for _ in range(6)]
blue_t = {
    1: 1,
    2: 3,
    3: 2
}
blue_idx = {
    1: lambda x: 3 - x,
    2: lambda x: 3 - x,
    3: lambda x: 2 - x,
}
score = 0
for t, x, y in [map(int, input().split()) for _ in range(N)]:
    green_board = block_down(t, y, green_board)
    blue_board = block_down(blue_t[t], blue_idx[t](x), blue_board)
    green_board = removeIfNeed(green_board)
    blue_board = removeIfNeed(blue_board)
    green_board = processLighter(green_board)
    blue_board = processLighter(blue_board)

print(score)
print(
    len([0 for x in range(6) for y in range(4) if green_board[x][y] != 0]) +
    len([0 for x in range(6) for y in range(4) if blue_board[x][y] != 0])
)