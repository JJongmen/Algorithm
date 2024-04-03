N = int(input())
schedule = [list(map(int, input().split())) for _ in range(N)]

global answer
answer = 0
def dfs(day, profit):
    global answer
    if day == N:
        answer = max(answer, profit)
        return
    if day > N:
        return
    t, p = schedule[day]
    dfs(day + t, profit + p)
    dfs(day + 1, profit)

dfs(0, 0)
print(answer)