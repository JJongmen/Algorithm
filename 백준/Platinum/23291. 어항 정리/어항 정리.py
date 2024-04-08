N, K = map(int, input().split())
fishes = list(map(lambda x: [int(x)], input().split()))

def clean():
    # 1. 물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣는다. (여러 개라면 모두 1개씩)
    global fishes
    min_ = min(fishes)
    for i, cnt in enumerate(fishes):
        if min_ == cnt: fishes[i] = [min_[0] + 1]
    
    # 2. 어항 쌓기
    fishes[1].append(fishes.pop(0)[0])

    # 3. 공중 부양 -> 90도 회전 -> 어항 위에 올려놓기 (반복)
    while True:
        idx = 0
        while idx < len(fishes) and len(fishes[idx]) >= 2:
            idx += 1
        if len(fishes[0]) > len(fishes) - idx: break
        rotated = list(map(list, zip(*fishes[idx - 1::-1])))
        fishes = fishes[idx:]
        for i, x in enumerate(rotated):
            fishes[i].extend(x)

    # 4. 물고기 수 조절
    def control():
        global tmp, i, cnt, fishes
        tmp = [row[:] for row in fishes]
        for i in range(len(fishes)):
            for j in range(len(fishes[i])):
                if j < len(fishes[i]) - 1:
                    cnt = abs(fishes[i][j] - fishes[i][j + 1]) // 5
                    bigger = 1 if fishes[i][j] > fishes[i][j + 1] else -1
                    tmp[i][j] -= bigger * cnt
                    tmp[i][j + 1] += bigger * cnt
                if i < len(fishes) - 1 and j < len(fishes[i + 1]):
                    cnt = abs(fishes[i][j] - fishes[i + 1][j]) // 5
                    bigger = 1 if fishes[i][j] > fishes[i + 1][j] else -1
                    tmp[i][j] -= bigger * cnt
                    tmp[i + 1][j] += bigger * cnt
        fishes = tmp

    control()

    # 5. 바닥에 일렬로 놓기
    def line_up():
        global tmp, x, fishes
        tmp = []
        for x in fishes:
            tmp.extend(x)
        fishes = list(map(lambda x: [x], tmp))

    line_up()

    # 6. 공중 부양
    fishes = list(map(lambda x: x[0], fishes))
    L = len(fishes) // 4
    fishes = list(map(list, zip(*[fishes[-L:], fishes[L - 1::-1], fishes[L:2 * L], fishes[-L - 1:2 * L - 1:-1]])))

    # 7. 물고기 수 조절
    control()

    # 8. 바닥에 일렬로 놓기
    line_up()

answer = 0
while max(fishes)[0] - min(fishes)[0] > K:
    clean()
    answer += 1
print(answer)
