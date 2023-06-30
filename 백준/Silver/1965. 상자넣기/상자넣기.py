N = int(input())
boxes = list(map(int, input().split()))
dp = [0] * N
for i in range(N):
    dp[i] = max([dp[j] for j in range(i) if boxes[j] < boxes[i]], default=0) + 1
print(max(dp))