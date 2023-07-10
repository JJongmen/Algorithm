import sys

N = int(sys.stdin.readline())
log = [sys.stdin.readline().rstrip() for _ in range(N)]
tokens = " ".join(log).split("ENTER")
result = 0
for token in tokens:
    result += len(set(token.strip().split()))
print(result)