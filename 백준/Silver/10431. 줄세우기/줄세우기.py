import sys

P = int(sys.stdin.readline())
for _ in range(P):
    heights = list(map(int, sys.stdin.readline().rstrip().split()))
    N, heights = heights[0], heights[1:]
    line = [heights[0]]
    steps = 0
    for i in range(1, 20):
        student = heights[i]
        pos = len(line)
        for j in range(len(line)):
            if line[j] > student:
                pos = j
                break
        steps += len(line) - pos
        line.insert(pos, student)
    print(N, steps)