import sys

N = int(sys.stdin.readline())
seq = list(map(int, sys.stdin.readline().rstrip().split()))

def get_decreasing_to_end_cnt(N):
    cnt = 1
    for i in range(N - 1, 0, -1):
        if seq[i] > seq[i - 1]:
            return cnt
        cnt += 1
    return cnt

decreasing_to_end_cnt = get_decreasing_to_end_cnt(N)
if decreasing_to_end_cnt == N:
    print(-1)
else:
    swap_idx = 0
    for i in range(-1, -decreasing_to_end_cnt - 1, -1):
        if seq[-decreasing_to_end_cnt - 1] < seq[i]:
            swap_idx = i
            break
    seq[-decreasing_to_end_cnt - 1], seq[swap_idx] = seq[swap_idx], seq[-decreasing_to_end_cnt - 1]
    seq = seq[:-(decreasing_to_end_cnt)] + sorted(seq[-(decreasing_to_end_cnt):])
    print(*seq)