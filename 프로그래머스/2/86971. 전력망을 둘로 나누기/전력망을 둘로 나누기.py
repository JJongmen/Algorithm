def solution(n, wires):
    graph = [[] for _ in range(n + 1)]
    for a, b in wires:
        graph[a].append(b)
        graph[b].append(a)
    
    def dfs(cur):
        visit[cur] = True
        cnt = 1
        for nxt in graph[cur]:
            if visit[nxt]: continue
            cnt += dfs(nxt)
        return cnt
    
    answer = 10000
    for a, b in wires:
        graph[a].remove(b)
        graph[b].remove(a)
        
        visit = [False] * (n + 1)
        cnt = dfs(1)
        answer = min(answer, abs(n - 2 * cnt))
        
        graph[a].append(b)
        graph[b].append(a)
    return answer