import sys
import itertools

N = int(sys.stdin.readline())
for case in itertools.permutations(range(1, N + 1)):
    print(*case)