import sys

N = int(sys.stdin.readline())
nums = []


def convertNum():
    tmp = 0
    for i in range(0, len(num)):
        tmp += num[-(1 + i)] * (10 ** i)
    return tmp


for _ in range(N):
    line = sys.stdin.readline().rstrip()
    num = []
    for ch in line:
        if ch.isdigit():
            num.append(int(ch))
        else:
            if num:
                nums.append(convertNum())
                num = []
    if num:
        nums.append(convertNum())
nums.sort()
print(*nums, sep = '\n')
