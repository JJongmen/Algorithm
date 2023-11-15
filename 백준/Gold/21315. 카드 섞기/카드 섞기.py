import sys
from math import log2, ceil

input = sys.stdin.readline

N = int(input())
cards = list(map(int, input().rstrip().split()))

def shuffle(cards, K):
    if K == -1:
        return cards
    return shuffle(cards[-2**K:], K-1) + cards[:-2**K]


for k1 in range(1, ceil(log2(N))):
    shuffled = shuffle(list(range(1, N + 1)), k1)
    for k2 in range(1, ceil(log2(N))):
        if shuffle(shuffled, k2) == cards:
            print(k1, k2)
            sys.exit(0)