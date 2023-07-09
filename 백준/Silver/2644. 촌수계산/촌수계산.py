import sys

n = int(sys.stdin.readline())
a, b = map(int, sys.stdin.readline().rstrip().split())
m = int(sys.stdin.readline())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    x, y = map(int, sys.stdin.readline().rstrip().split())
    graph[x].append(y)
    graph[y].append(x)

que = [a]
visit = [False] * (n + 1)
visit[a] = True
result = 0
while que:
    size = len(que)
    for _ in range(size):
        cur = que.pop(0)
        if cur == b:
            print(result)
            exit()
        for nxt in graph[cur]:
            if visit[nxt]: continue
            que.append(nxt)
            visit[nxt] = True
    result += 1
print(-1)