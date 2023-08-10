import bisect
import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
titles, powers = [], []
for _ in range(N):
    title, power = sys.stdin.readline().rstrip().split()
    titles.append(title)
    powers.append(int(power))
for _ in range(M):
    power = int(sys.stdin.readline())
    idx = bisect.bisect_left(powers, power)
    print(titles[idx])