import sys
import heapq

N, D = map(int, sys.stdin.readline().rstrip().split())
graph = [[] for _ in range(D + 1)]
for i in range(1, D + 1):
    graph[i-1].append((i, 1))
for _ in range(N):
    start, end, dist = map(int, sys.stdin.readline().rstrip().split())
    if end <= D:
        graph[start].append((end, dist))

minDist = [float('inf')] * (D + 1)
minDist[0] = 0

def dijkstra():
    q = []
    heapq.heappush(q, (0, 0))

    while q:
        dist, now = heapq.heappop(q)

        if minDist[now] < dist:
            continue

        for nxt, cost in graph[now]:
            new_dist = dist + cost
            if new_dist < minDist[nxt]:
                minDist[nxt] = new_dist
                heapq.heappush(q, (new_dist, nxt))

dijkstra()

print(minDist[D])