N, K = map(int, input().split())
board = [[2] * (N + 2)] + [[2] + list(map(int, input().split())) + [2] for _ in range(N)] + [[2] * (N + 2)]
players = [list(map(int, input().split())) + [0] for _ in range(K)]

poses = [[[] for _ in range(N + 1)] for _ in range(N + 1)]
for player, info in enumerate(players):
    poses[info[0]][info[1]].append(player)

dx, dy = (0, 0, 0, -1, 1), (0, 1, -1, 0, 0) # (X, 오, 왼, 상, 하)
d_reverse = {
    1: 2,
    2: 1,
    3: 4,
    4: 3,
}
for turn in range(1, 1001):
    for player, (x, y, d, idx) in enumerate(players):
        # 1. 이동할 위치 결정
        nx, ny = x + dx[d], y + dy[d]
        if board[nx][ny] == 2:
            nx, ny = x - dx[d], y - dy[d]
            players[player][2] = d_reverse[d]
            # 이동할 공간이 모두 파랑(바깥)이면 방향만 바꾸고 다음 말로 넘어간다.
            if board[nx][ny] == 2:
                continue
        # 2. 이동할 말들 list에 담기
        move_players = [] # 역순으로 담음
        for i in range(len(poses[x][y]) - idx):
            move_players.append(poses[x][y].pop())
        # 3. 이동
        if board[nx][ny] == 0: # 이동할 칸이 흰색
            while move_players:
                move_player = move_players.pop()
                poses[nx][ny].append(move_player)
                players[move_player][0] = nx
                players[move_player][1] = ny
                players[move_player][3] = len(poses[nx][ny]) - 1
        else: # 이동할 칸이 빨간색
            for move_player in move_players:
                poses[nx][ny].append(move_player)
                players[move_player][0] = nx
                players[move_player][1] = ny
                players[move_player][3] = len(poses[nx][ny]) - 1
        # 4. 이동한 칸에 말이 4개 이상 있는 경우 게임 종료
        if len(poses[nx][ny]) >= 4:
            print(turn)
            exit()
print(-1)
