import sys

counts = [3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1]

A = sys.stdin.readline().rstrip()
B = sys.stdin.readline().rstrip()
memo = []
for i in range(len(A)):
    memo.append(counts[ord(A[i]) - ord('A')])
    memo.append(counts[ord(B[i]) - ord('A')])

for i in range(2 * len(A) - 1, 1, -1):
    for j in range(0, i):
        memo[j] = (memo[j] + memo[j + 1]) % 10
print(memo[0], memo[1], sep='')