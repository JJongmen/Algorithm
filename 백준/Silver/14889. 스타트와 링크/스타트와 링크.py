N = int(input())
stats = [list(map(int, input().split())) for _ in range(N)]

answer = float('inf')
start_team = [0]
def bt(num, start_stat):
    if len(start_team) == N // 2:
        global answer
        link_team = [x for x in range(N) if x not in start_team]
        link_stat = sum([stats[x][y] + stats[y][x] for idx, x in enumerate(link_team) for y in link_team[idx + 1:]])
        answer = min(answer, abs(start_stat - link_stat))
        return
    start_team.append(num)
    bt(num + 1, start_stat + sum([stats[x][num] + stats[num][x] for x in start_team]))
    start_team.pop()
    if N // 2 - len(start_team) < N - num:
        bt(num + 1, start_stat)
bt(1, 0)
print(answer)