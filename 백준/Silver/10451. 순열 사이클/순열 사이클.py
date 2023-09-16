import sys

sys.setrecursionlimit(10**6)

parent = [i for i in range(1001)]

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x = find(x)
    y = find(y)
    if x != y:
        parent[y] = x

T = int(sys.stdin.readline())
for _ in range(T):
    parent = [i for i in range(1001)]
    N = int(sys.stdin.readline())
    seq = list(map(int, sys.stdin.readline().rstrip().split()))
    for i in range(1, N + 1):
        union(i, seq[i - 1])
    print(len(set([find(i) for i in range(1, N + 1)])))
    