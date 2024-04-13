import sys
input = sys.stdin.readline

N, M, H = map(int, input().split())
ladder = [[False] * (N + 1) for _ in range(H + 1)]
for _ in range(M):
    x, y = map(int, input().split())
    ladder[x][y] = True

def down_all():
    for num in range(1, N + 1):
        y = num
        for x in range(1, H + 1):
            # 오른쪽 이동
            if ladder[x][y]:
                y += 1
            # 왼쪽 이동
            elif ladder[x][y - 1]:
                y -= 1
            x += 1
        if num != y:
            return False
    return True

def bt(cnt, x, y):
    global answer
    if cnt >= answer: return
    if down_all():
        answer = min(answer, cnt)
        return
    if x == H + 1 or cnt == 3:
        return
    for j in range(y, N):
        if ladder[x][j]: continue
        ladder[x][j] = True
        if j < N - 2:
            bt(cnt + 1, x, j + 2)
        else:
            bt(cnt + 1, x + 1, 1)
        ladder[x][j] = False
    for i in range(x + 1, H + 1):
        for j in range(1, N):
            if ladder[i][j]: continue
            ladder[i][j] = True
            if j < N - 2:
                bt(cnt + 1, i, j + 2)
            else:
                bt(cnt + 1, i + 1, 1)
            ladder[i][j] = False


answer = 4
bt(0, 1, 1)
print(-1 if answer == 4 else answer)