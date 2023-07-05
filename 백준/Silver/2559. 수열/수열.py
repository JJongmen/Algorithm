import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
temps = list(map(int, sys.stdin.readline().rstrip().split()))


max_sum = cur_sum = sum(temps[:K])
for i in range(K, N):
    cur_sum += temps[i] - temps[i - K]
    max_sum = max(max_sum, cur_sum)

print(max_sum)