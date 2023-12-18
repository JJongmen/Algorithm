import sys
from math import comb

input = sys.stdin.readline

N = int(input())

wordDict = {}
for _ in range(N):
    word = input().rstrip()
    alphaDict = {}
    str_ = ''
    for ch in word:
        if not ch in alphaDict:
            alphaDict[ch] = str(len(alphaDict))
        str_ += alphaDict[ch]
    if not str_ in wordDict:
        wordDict[str_] = 1
    else:
        wordDict[str_] += 1

result = 0
for k in wordDict:
    result += comb(wordDict[k], 2)
print(result)