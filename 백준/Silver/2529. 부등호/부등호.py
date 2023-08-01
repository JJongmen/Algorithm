import sys

k = int(sys.stdin.readline())
signs = list(sys.stdin.readline().rstrip().split())

isUsed = [False] * 10
max_, min_ = "", ""

def promising(sign, a, b):
    if sign == "<":
        return a < b
    return a > b

def bt(idx, comb):
    global max_, min_
    if idx == k + 1:
        if not min_: min_ = comb
        else: max_ = comb
        return
    for i in range(10):
        if isUsed[i]: continue
        if idx == 0 or promising(signs[idx - 1], int(comb[-1]), i):
            isUsed[i] = True
            bt(idx + 1, comb + str(i))
            isUsed[i] = False

bt(0, "")
print(max_)
print(min_)
