import sys

input = sys.stdin.readline

N = int(input())
words = [0] * N
for i in range(N):
    words[i] = input().rstrip()
words.sort()

result = 1
for i in range(N - 1):
    if words[i] != words[i + 1][:len(words[i])]:
        result += 1
print(result)