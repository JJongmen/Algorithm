import sys

N = int(sys.stdin.readline())
higherCnts = list(map(int, sys.stdin.readline().rstrip().split())) 

result = [0] * N
for num in range(N):
    height = num + 1
    cnt = 0
    for pos in range(N):
        if higherCnts[num] == cnt and result[pos] == 0:
            result[pos] = height
            break
        elif result[pos] > height or result[pos] == 0:
            cnt += 1
print(*result)    