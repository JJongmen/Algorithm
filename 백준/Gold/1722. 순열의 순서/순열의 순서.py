import sys

N = int(sys.stdin.readline())
fact = [1]
for i in range(1, N + 1):
    fact.append(fact[-1] * i)
line = list(map(int, sys.stdin.readline().rstrip().split()))
nums = list(range(1, N + 1))
if line[0] == 1:
    k = line[1] - 1
    result = []
    for i in range(N - 1, -1, -1):
        result.append(nums.pop(k // fact[i]))
        k %= fact[i]
    print(*result)
else:
    seq = line[1:]
    result = 0
    for num in seq:
        result += fact[N - 1] * nums.index(num)
        nums.remove(num)
        N -= 1
    print(result + 1)