import sys

S = sys.stdin.readline().rstrip()
q = int(sys.stdin.readline())
for _ in range(q):
    a, b, c = sys.stdin.readline().rstrip().split()
    b, c = int(b), int(c)
    print(S.count(a, b, c + 1))
