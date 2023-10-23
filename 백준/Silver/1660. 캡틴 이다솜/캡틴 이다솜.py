import sys

input = sys.stdin.readline

N = int(input())

tetrahedron = []
i = 1
k = 0
while k < N:
    k += (i*(i+1))//2
    tetrahedron.append(k)
    i += 1

dp = [float('inf')]*(N+1)
dp[0] = 0
for i in range(1, N+1):
    for j in tetrahedron:
        if i < j: break
        dp[i] = min(dp[i], dp[i - j] + 1)
print(dp[N])