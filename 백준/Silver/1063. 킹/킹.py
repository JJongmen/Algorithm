import sys

K, R, N = sys.stdin.readline().rstrip().split()
N = int(N)

def chess2pos(chess):
    return [9 - int(chess[1]), ord(chess[0]) - ord('A') + 1]

def pos2chess(pos):
    return chr(ord('A') + pos[1] - 1) + str(9 - pos[0])

dx = [0, 0, 1, -1, -1, -1, 1, 1]
dy = [1, -1, 0, 0, 1, -1, 1, -1]
cmdList = ["R", "L", "B", "T", "RT", "LT", "RB", "LB"]
cmd_dict = {cmd: idx for idx, cmd in enumerate(cmdList)}

K = chess2pos(K)
R = chess2pos(R)

for _ in range(N):
    cmd = sys.stdin.readline().rstrip()
    d = cmd_dict[cmd]
    nx, ny = K[0] + dx[d], K[1] + dy[d]
    # 킹이 체스판 밖으로 나간 경우 무효
    if nx < 1 or nx > 8 or ny < 1 or ny > 8:
        continue
    # 이동하려는 방향에 돌이 이미 있는 경우
    if R == [nx, ny]:
        nrx, nry = R[0] + dx[d], R[1] + dy[d]
        # 돌이 체스판 밖으로 나간 경우 무효
        if nrx < 1 or nrx > 8 or nry < 1 or nry > 8:
            continue
        R = [nrx, nry]
    K = [nx, ny]
print(pos2chess(K))
print(pos2chess(R))