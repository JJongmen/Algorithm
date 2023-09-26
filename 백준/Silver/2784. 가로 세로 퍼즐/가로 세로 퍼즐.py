import sys
import itertools
import collections

words = [sys.stdin.readline().rstrip() for _ in range(6)]

board = [[] * 3 for _ in range(3)]

select_words = [0] * 3
isUsed = [False] * 6

answer_counter = collections.Counter(words)

cases = itertools.permutations(words, 3)
for case in cases:
    counter = collections.Counter()
    counter.update(case)
    col_words = []
    for i in range(3):
        col_words.append(case[0][i] + case[1][i] + case[2][i])
    counter.update(col_words)
    if answer_counter == counter:
        print(*case, sep='\n')
        sys.exit(0)
print(0)