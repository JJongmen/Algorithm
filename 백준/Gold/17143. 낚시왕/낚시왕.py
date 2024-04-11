R, C, M = map(int, input().split())
sharks = [list(map(int, input().split())) for _ in range(M)]

sharks.sort(key=lambda x:x[4])
sharks = {z:(r - 1, c - 1, s, d) for r, c, s, d, z in sharks}

dx, dy = (0, -1, 1, 0, 0), (0, 0, 0, 1, -1) # (위, 아래, 오른쪽, 왼쪽)
def move(num):
    r, c, s, d = sharks[num]
    # 위 또는 아래
    if d == 1 or d == 2:
        if d == 1:
            r = 2 * R - 2 - r
        r += s
        r %= (2 * R - 2)
        if r < R:
            d = 2
        else:
            r = 2 * R - 2 - r
            d = 1
    # 오른쪽 또는 왼쪽
    else:
        if d == 4:
            c = 2 * C - 2 - c
        c += s
        c %= (2 * C - 2)
        if c < C:
            d = 3
        else:
            c = 2 * C - 2 - c
            d = 4
    return r, c, s, d

catch = 0
idx = -1
pos = {tuple(sharks[num][:2]):num for num in sharks}
while True:
    # 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
    idx += 1
    if idx == C: break
    # 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
    for i in range(R):
        if (i, idx) in pos:
            num = pos[(i, idx)]
            catch += num
            del sharks[num]
            break
    # 3. 상어가 이동한다.
    pos = {}
    shark_nums = list(sharks.keys())
    for num in shark_nums:
        r, c, s, d = move(num)
        # 작은 상어가 있으면 잡아먹는다.
        if (r, c) in pos:
            small_num = pos[(r, c)]
            del sharks[small_num]
        pos[(r, c)] = num
        sharks[num] = (r, c, s, d)
print(catch)