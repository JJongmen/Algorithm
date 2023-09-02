import sys

line = sys.stdin.readline().rstrip()
N, N_idx = 1, 0
idx = 0
len_ = len(line)
while True:
    ch = line[idx]
    cur = list(str(N))
    while N_idx < len(cur):
        if cur[N_idx] == ch:
            idx += 1
            N_idx += 1
            break
        N_idx += 1
    if idx == len_: break
    if N_idx == len(cur):
        N += 1
        N_idx = 0
print(N)
