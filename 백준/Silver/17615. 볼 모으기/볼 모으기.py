import sys

N = int(sys.stdin.readline())
balls = sys.stdin.readline().rstrip()

counts = []
count = 0
prev = None
for ball in balls:
    if not prev or prev == ball:
        count += 1
    else:
        counts.append(count)
        count = 1
    prev = ball
counts.append(count)

print(min(sum(counts[:-1:2]), sum(counts[2::2]), sum(counts[1:-1:2]), sum(counts[1::2])))