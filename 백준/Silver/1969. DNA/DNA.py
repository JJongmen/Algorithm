import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
dna = []
for _ in range(N):
    dna.append(sys.stdin.readline().rstrip())

result_dna = []
result = 0
for j in range(M):
    counts = [0] * 26
    for i in range(N):
        counts[ord(dna[i][j]) - ord('A')] += 1
    maxCnt = 0
    maxIdx = 0
    for i in range(26):
        if maxCnt < counts[i]:
            maxCnt = counts[i]
            maxIdx = i
    result_dna.append(chr(ord('A') + maxIdx))
    result += N - maxCnt
print(''.join(result_dna))
print(result)