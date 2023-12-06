import sys
from collections import Counter

input = sys.stdin.readline

T = input().rstrip()
want_counts = Counter(T)
N = int(input())
infos = [input().rstrip().split() for _ in range(N)]

def bf(idx, counter: Counter, price):
    global min_
    if idx == N:
        if not (want_counts - counter):
            min_ = min(min_, price)
        return
    if price >= min_: return
    bf(idx + 1, counter, price)
    counter_ = counter + Counter(infos[idx][1])
    bf(idx + 1, counter_, price + int(infos[idx][0]))

min_ = float('inf')
bf(0, Counter(), 0)

print(min_ if min_ != float('inf') else -1)