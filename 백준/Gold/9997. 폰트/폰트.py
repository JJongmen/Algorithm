import sys

TEST_SENTENCE_MASK = (1 << 26) - 1

N = int(sys.stdin.readline())
words = []
for _ in range(N):
    word = sys.stdin.readline().rstrip()
    bit = 0
    for ch in word:
        bit |= (1 << (ord(ch) - ord('a')))
    words.append(bit)

result = 0

def brute(idx, mask):
    global result
    if mask == TEST_SENTENCE_MASK:
        result += 1 << (N - idx)
        return
    if idx == N:
        return
    brute(idx + 1, mask)
    brute(idx + 1, mask | words[idx])

brute(0, 0)
print(result)