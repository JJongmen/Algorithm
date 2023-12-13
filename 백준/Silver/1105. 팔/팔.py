import sys

input = sys.stdin.readline

L, R = input().rstrip().split()

if len(L) != len(R):
    print(0)
else:
    result = 0
    for i in range(len(L)):
        if L[i] != R[i]:
            break
        if L[i] == '8':
            result += 1
    print(result)