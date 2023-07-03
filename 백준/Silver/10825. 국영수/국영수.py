import sys

N = int(sys.stdin.readline())
scores = [sys.stdin.readline().rstrip().split() for _ in range(N)]
scores = sorted(scores, key=lambda x: (-int(x[1]), int(x[2]), -int(x[3]), x))
print(*[score[0] for score in scores], sep='\n')