import sys

input = sys.stdin.readline

def dfs(l, r, cur_score):
    global score
    if l == N or r == N:
        score = max(score, cur_score)
        return
    if dp[l][r] >= cur_score: return
    dp[l][r] = cur_score
    if A[l] > B[r]:
        dfs(l, r + 1, cur_score + B[r])
    dfs(l + 1, r, cur_score)
    dfs(l + 1, r + 1, cur_score)

N = int(input())
A = list(map(int, input().rstrip().split()))
B = list(map(int, input().rstrip().split()))

dp = [[-1] * N for _ in range(N)]
score = 0

dfs(0, 0, 0)

print(score)
