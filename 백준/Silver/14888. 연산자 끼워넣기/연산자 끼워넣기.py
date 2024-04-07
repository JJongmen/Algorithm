N = int(input())
A = list(map(int, input().split()))
operators = list(map(int, input().split()))

min_, max_ = 1000000001, -1000000001
calc = {
    0: lambda x, y: x + y,
    1: lambda x, y: x - y,
    2: lambda x, y: x * y,
    3: lambda x, y: x // y if x >= 0 else -(-x // y),
}
def dfs(idx, result):
    if idx == len(A):
        global min_, max_
        min_ = min(min_, result)
        max_ = max(max_, result)
        return
    for i in range(4):
        if operators[i] > 0:
            operators[i] -= 1
            dfs(idx + 1, calc[i](result, A[idx]))
            operators[i] += 1

dfs(1, A[0])
print(max_)
print(min_)