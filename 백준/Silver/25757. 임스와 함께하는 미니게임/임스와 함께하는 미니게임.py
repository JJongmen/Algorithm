import sys

N, gameType = sys.stdin.readline().rstrip().split()
N = int(N)

players = set([sys.stdin.readline().rstrip() for _ in range(N)])
if gameType == 'Y':
    print(len(players))
elif gameType == 'F':
    print(len(players) // 2)
else:
    print(len(players) // 3)