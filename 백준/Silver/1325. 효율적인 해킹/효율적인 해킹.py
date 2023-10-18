import sys
from collections import deque

N, M = map(int, sys.stdin.readline().rstrip().split())
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    A, B = map(int, sys.stdin.readline().rstrip().split())
    graph[B].append(A)

result = []
max_val = 0
for i in range(1, N + 1):
    visited = [False] * (N + 1)
    que = deque([i])
    visited[i] = True
    cnt = 1
    while que:
        cur = que.popleft()
        for nxt in graph[cur]:
            if visited[nxt]: continue
            visited[nxt] = True
            que.append(nxt)
            cnt += 1
    if max_val == cnt:
        result.append(i)
    elif max_val < cnt:
        max_val = cnt
        result = [i]
print(*result)