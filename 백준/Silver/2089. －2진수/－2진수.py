import math

N = int(input())
if N == 0:
    print(0)
    exit()
ans = []
while N != 0:
    if N % 2 == 0:
        N = int(N / -2)
        ans.append(0)
    else:
        N = int(math.ceil(N / -2))
        ans.append(1)
print(*ans[::-1], sep='')