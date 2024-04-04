dices = list(map(int, input().split()))

scores = [2 * x for x in range(21)] + list(range(21, 33))
scores[21] = 13
scores[23] = 16
scores[29] = 19
scores[31] = 35
scores[32] = 0
board = [[x + 1] for x in range(20)] + [[] for _ in range(12)]
board[5].append(21)
board[21].append(23)
board[23].append(29)
board[29].append(25)
board[10].append(22)
board[22].append(24)
board[24].append(25)
board[15].append(28)
board[28].append(27)
board[27].append(26)
board[26].append(25)
board[25].append(30)
board[30].append(31)
board[31].append(20)
board[20].append(-1)

global answer
answer = 0


def move(pos, dist, startBlue):
    if pos == -1 or dist == 0:
        return pos
    if startBlue:
        return move(board[pos][-1], dist - 1, startBlue)
    return move(board[pos][0], dist - 1, startBlue)


def dfs(players, dice_idx, score):
    if dice_idx == 10:
        global answer
        answer = max(answer, score)
        return
    for i in range(4):
        cur = players[i]
        # 도착 칸에 있는 말은 더 이상 이동할 수 없다.
        if cur == -1: continue
        nxt = move(cur, dices[dice_idx], len(board[cur]) > 1)
        # 도착 칸을 제외하고 이동을 마치는 칸에 다른 말이 있으면 고를 수 없다.
        if nxt != -1 and nxt in players: continue
        nxt_players = players[:]
        nxt_players[i] = nxt
        dfs(nxt_players, dice_idx + 1, score + scores[nxt])

dfs([0, 0, 0, 0], 0, 0)
print(answer)