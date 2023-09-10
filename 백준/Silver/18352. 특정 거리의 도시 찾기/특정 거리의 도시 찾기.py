import sys
import heapq

N, M, K, X = map(int, sys.stdin.readline().rstrip().split())
graph = [[] for _ in range(N + 1)]
for _ in range(M):
    a, b = map(int, sys.stdin.readline().rstrip().split())
    graph[a].append(b)

dists = [float('inf')] * (N + 1)
dists[X] = 0

que = []
heapq.heappush(que, (0, X))
while que:
    dist, cur = heapq.heappop(que)
    if dist != dists[cur]: continue
    for nxt in graph[cur]:
        if dists[nxt] <= dist + 1: continue
        dists[nxt] = dist + 1
        heapq.heappush(que, (dist + 1, nxt))

cities = [city for city in range(1, N + 1) if dists[city] == K]
if cities:
    for city in cities:
        print(city)
else:
    print(-1)