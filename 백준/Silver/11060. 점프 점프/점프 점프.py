import sys

N = int(sys.stdin.readline())
maze = list(map(int, sys.stdin.readline().rstrip().split()))

if N == 1:
    print(0)
    exit(0)

memo = [0] * N
que = [0]
while que:
    cur = que.pop(0)
    for jump in range(1, maze[cur] + 1):
        nxt = cur + jump
        if nxt >= N: continue
        if memo[nxt] != 0: continue
        que.append(nxt)
        memo[nxt] = memo[cur] + 1
        if nxt == N - 1:
            print(memo[nxt])
            exit(0)
print(-1)