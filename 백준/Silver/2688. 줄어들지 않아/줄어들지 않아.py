import sys

counts = [0, 10]
for i in range(1, 65):
    counts.append(counts[i] * (10 + i) / (i+1))

T = int(sys.stdin.readline())
for _ in range(T):
    N = int(sys.stdin.readline())
    print(int(counts[N]))