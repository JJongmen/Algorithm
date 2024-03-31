def solution(storey):
    global answer
    answer = 10000
    def dfs(num, k, cnt):
        global answer
        a = num // (10 ** k) % 10
        if num == 0:
            answer = min(answer, cnt)
            return
        dfs(num - a * (10**k), k + 1, cnt + a)
        if num != 10 ** k:
            dfs(num + (10 - a) * (10**k), k + 1, cnt + 10 - a)
    dfs(storey, 0, 0)
    return answer