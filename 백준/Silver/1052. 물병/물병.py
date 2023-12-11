import sys

input = sys.stdin.readline

N, K = map(int, input().rstrip().split())

bin_ = bin(N)[2:]
if bin_.count('1') <= K:
    print(0)
    exit()

idx = -1
cnt = 0
while cnt < K:
    idx += 1
    if bin_[idx] == '1':
        cnt += 1
print((1 << (len(bin_) - idx - 1)) - int(bin_[idx + 1:], 2))