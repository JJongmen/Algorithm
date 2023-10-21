import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    A, B = map(int, input().rstrip().split())
    graph[A].append(B)
    graph[B].append(A)

result = [0, 0, float('inf')]
for i in range(1, N):
    for j in range(i + 1, N + 1):
        dist = [float('inf')] * (N + 1)
        que = deque([i, j])
        dist[i] = dist[j] = 0
        while que:
            len_ = len(que)
            for _ in range(len_):
                cur = que.popleft()
                for nxt in graph[cur]:
                    if dist[nxt] != float('inf'): continue
                    dist[nxt] = dist[cur] + 1
                    que.append(nxt)
        sum_ = 2 * sum(dist[1:])
        if result[2] > sum_:
            result = [i, j, sum_]
print(*result)
