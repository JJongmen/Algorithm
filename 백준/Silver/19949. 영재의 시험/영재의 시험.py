import sys

input = sys.stdin.readline
answers = list(map(int, input().rstrip().split()))

choose = [0] * 10
result = 0
def dfs(cur):
    global result
    if cur == 10:
        cnt = 0
        for i in range(10):
            if choose[i] == answers[i]:
                cnt += 1
        if cnt >= 5:
            result += 1
        return
    for i in range(1, 6):
        if cur >= 2 and choose[cur - 2] == i and choose[cur - 1] == i:
            continue
        choose[cur] = i
        dfs(cur + 1)
dfs(0)
print(result)