import sys

N = int(sys.stdin.readline())
count = 0
for _ in range(N):
    word = sys.stdin.readline().rstrip()
    stack = []
    for ch in word:
        if stack and stack[-1] == ch:
            stack.pop()
        else:
            stack.append(ch)
    if not stack:
        count += 1
print(count)