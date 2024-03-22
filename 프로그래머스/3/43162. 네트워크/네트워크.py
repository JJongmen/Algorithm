visit = [False] * 200
graph = [[] for _ in range(200)]

def dfs(cur):
    visit[cur] = True
    for nxt in graph[cur]:
        if visit[nxt]: continue
        dfs(nxt)

def solution(n, computers):
    answer = 0
    
    for i, computer in enumerate(computers):
        for j in range(n):
            if i == j: continue
            if computer[j] == 1:
                graph[i].append(j)
    
    for i in range(n):
        if visit[i]: continue
        dfs(i)
        answer += 1
            
    return answer