import sys

isPrime = [True] * 100001
isPrime[0] = isPrime[1] = False
for i in range(2, 100001):
    if not isPrime[i]: continue
    for j in range(i * i, 100001, i):
        isPrime[j] = False

input = sys.stdin.readline

while True:
    str_ = input().rstrip()
    if str_ == '0': break
    result = 0
    for i in range(len(str_)):
        for j in range(i + 1, min(len(str_) + 1, i + 6)):
            if isPrime[int(str_[i:j])]:
                result = max(result, int(str_[i:j]))
    print(result)