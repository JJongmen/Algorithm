import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
line = sys.stdin.readline().rstrip()

isEaten = [False] * N
cnt = 0
for i in range(N):
    if line[i] == 'P':
        for j in range(max(0, i - K), min(N, i + K + 1)):
            if line[j] == 'H' and not isEaten[j]:
                isEaten[j] = True
                cnt += 1
                break
print(cnt)