import sys

A, B = map(int, sys.stdin.readline().rstrip().split()) # 가로, 세로
N, M = map(int, sys.stdin.readline().rstrip().split()) # 로봇 개수, 명령 개수

d_dict = {'N': 0, 'E': 1, 'S': 2, 'W': 3}
dx = (1, 0, -1, 0)
dy = (0, 1, 0, -1)
board = [[0] * A for _ in range(B)]
robots = [[-1, -1, -1]]
for i in range(1, N + 1):
    x, y, d = sys.stdin.readline().rstrip().split()
    x, y, d = int(x), int(y), d_dict[d]
    robots.append([y-1, x-1, d])
    board[y-1][x-1] = i

result = 'OK'
for _ in range(M):
    robot, cmd, repeat = sys.stdin.readline().rstrip().split()
    robot, repeat = int(robot), int(repeat)
    if result != 'OK': continue
    if cmd == 'L':
        robots[robot][2] = (robots[robot][2] + 100 - repeat) % 4
    elif cmd == 'R':
        robots[robot][2] = (robots[robot][2] + repeat) % 4
    else:
        x, y, d = robots[robot]
        nx, ny = x, y
        for _ in range(repeat):
            nx, ny = nx + dx[d], ny + dy[d]
            if nx < 0 or nx >= B or ny < 0 or ny >= A:
                result = f'Robot {robot} crashes into the wall'
                break
            if board[nx][ny] != 0:
                result = f'Robot {robot} crashes into robot {board[nx][ny]}'
                break
        if result != 'OK': continue
        board[x][y] = 0
        board[nx][ny] = robot
        robots[robot][0], robots[robot][1] = nx, ny

print(result)