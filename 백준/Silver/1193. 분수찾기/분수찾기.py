import sys

X = int(sys.stdin.readline())

cnt = 1
idx = 1
while cnt < X:
    idx += 1
    cnt += idx

if idx % 2 == 0:
    print(str(idx - (cnt - X)) + "/" + str(1 + (cnt - X)))
else:
    print(str(1 + (cnt - X)) + "/" + str(idx - (cnt - X)))