import sys

N = int(sys.stdin.readline())
seq = list(map(int , sys.stdin.readline().rstrip().split()))

len_ = 1
for i in range(N - 1, 0, -1):
    if seq[i - 1] > seq[i]:
        break
    len_ += 1

if len_ == N:
    print(-1)
else:
    target = seq[i - 1]
    for j in range(N - 1, i - 1, -1):
        if seq[j] < target:
            break
    seq[i - 1], seq[j] = seq[j], seq[i - 1]
    seq = seq[:i] + sorted(seq[i:], reverse=True)
    print(' '.join(map(str, seq)))