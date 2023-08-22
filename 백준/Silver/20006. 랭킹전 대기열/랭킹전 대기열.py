import sys

P, M = map(int, sys.stdin.readline().rstrip().split())

rooms = []
for _ in range(P):
    L, N = sys.stdin.readline().rstrip().split()
    L = int(L)
    enterFlag = False
    for room in rooms:
        if len(room) == M: continue
        if abs(room[0][0] - L) > 10: continue
        room.append((L, N))
        enterFlag = True
        break
    if not enterFlag:
        rooms.append([(L, N)])

for room in rooms:
    room.sort(key=lambda x: x[1])
    if len(room) == M:
        print("Started!")
    else:
        print("Waiting!")
    for player in room:
        print(*player)