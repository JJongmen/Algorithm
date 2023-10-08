import sys

N = int(sys.stdin.readline())

# N의 자릿수
L = len(str(N))
result = 0
for i in range(L - 1):
    result += 9 * (10 ** i) * (i + 1)
result += (N - (10 ** (L - 1)) + 1) * L
print(result)