import sys

wordA = sys.stdin.readline().rstrip()
wordB = sys.stdin.readline().rstrip()

cnt = 0
i = 0
while i < len(wordA):
    if wordA[i:i+len(wordB)] == wordB:
       cnt += 1
       i += len(wordB)
    else:
        i += 1

print(cnt)