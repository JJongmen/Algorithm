import sys

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
positions = list(map(int, sys.stdin.readline().rstrip().split()))

dists = [positions[i + 1] - positions[i] for i in range(M - 1)]
dists.append(0)
print(max(positions[0], N - positions[-1], (max(dists) + 1) // 2))