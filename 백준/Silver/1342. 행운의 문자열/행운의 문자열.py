import sys

def bt(cur, cnt):
    global S, result
    if len(cur) == len(S):
        result += 1
        return
    
    for i in range(26):
        if counts[i] > 0 and cur[cnt - 1] != chr(ord('a') + i):
            nxt = cur + chr(ord('a') + i)
            counts[i] -= 1
            bt(nxt, cnt + 1)
            counts[i] += 1
        

input = sys.stdin.readline

S = input().rstrip()

counts = [0] * 26

for ch in S:
    counts[ord(ch) - ord('a')] += 1

cur = ""
result = 0
for i in range(26):
    if counts[i] == 0: continue
    nxt = cur + chr(ord('a') + i)
    counts[i] -= 1
    bt(nxt, 1)
    counts[i] += 1

print(result)