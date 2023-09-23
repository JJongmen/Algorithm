import sys

mapping = {'A': 1, 'B': 2, 'C': 3, 'D': 4, 'E': 5, 
           'F': 6, 'G': 7, 'H': 8, 'I': 9, 'J': 10,
           'K': 11, 'L': 12, '.': -1, 'x': 0}
reverse_mapping = {v: k for k, v in mapping.items()}
points = [(0, 4), (1, 1), (1, 3), (1, 5), (1, 7), (2, 2), (2, 6)
          , (3,1), (3, 3), (3, 5), (3, 7), (4, 4)]
lines = [(0, 2, 5, 7), (0, 3, 6, 10), (1, 2, 3, 4), (1, 5, 8, 11)
         , (4, 6, 9, 11), (7, 8, 9, 10)]
fixed = [False] * 12
isUsed = [False] * 13
find_answer = False


board = [list(map(lambda x: mapping[x], sys.stdin.readline().rstrip())) for _ in range(5)]

for idx, point in enumerate(points):
    x, y = point
    if board[x][y] > 0:
        fixed[idx] = True
        isUsed[board[x][y]] = True

def promising():
    for line in lines:
        sum_ = 0
        for idx, point_idx in enumerate(line):
            x, y = points[point_idx]
            if board[x][y] == 0: break
            sum_ += board[x][y]
            if idx == 3 and sum_ != 26: return False
    return True


def bt(cur):
    global find_answer
    if cur == 12:
        if promising():
            for row in board:
                print(''.join(map(lambda x: reverse_mapping[x], row)))
            find_answer = True
        return
    if fixed[cur]:
        bt(cur + 1)
        return
    for num in range(1, 13):
        if isUsed[num]: continue
        isUsed[num] = True
        board[points[cur][0]][points[cur][1]] = num
        if promising():
            bt(cur + 1)
        if find_answer: return
        board[points[cur][0]][points[cur][1]] = 0
        isUsed[num] = False

bt(0)
