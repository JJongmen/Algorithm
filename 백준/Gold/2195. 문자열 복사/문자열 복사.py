import sys

S = sys.stdin.readline().rstrip()
P = sys.stdin.readline().rstrip()

p_idx = 0
result = 0
while True:
    start = P[p_idx]
    tmp = 0
    long_s_idx, long_cnt = 0, 0
    while True:
        s_idx = S.find(start, tmp)
        if s_idx == -1: break
        same_cnt = 0
        while True:
            if p_idx + same_cnt >= len(P): break
            if s_idx + same_cnt >= len(S): break
            if P[p_idx + same_cnt] != S[s_idx + same_cnt]: break
            same_cnt += 1
        if same_cnt > long_cnt:
            long_cnt = same_cnt
            long_s_idx = s_idx
        tmp = s_idx + 1
    result += 1
    p_idx += long_cnt
    if p_idx >= len(P): break
print(result)