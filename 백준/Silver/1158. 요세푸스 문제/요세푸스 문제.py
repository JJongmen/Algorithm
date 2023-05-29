N, K = map(int, input().split())
table = [i for i in range(1, N + 1)]

idx = 0
ans = []
for i in range(N):
    idx = (idx - 1 + K) % len(table)
    ans.append(table[idx])
    del table[idx]

print('<', end='')
print(*ans, sep=', ', end='')
print('>')