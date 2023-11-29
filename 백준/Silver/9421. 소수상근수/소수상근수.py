def isSangGeunNumber(n):
    if n == 1:
        return True
    strN = str(n)
    sum_ = 0
    for i in range(len(strN)):
        num = int(strN[i])
        sum_ += num ** 2
    if sum_ in usedNums:
        return False
    usedNums.add(sum_)
    return isSangGeunNumber(sum_)

isPrime = [True] * 1000001
isPrime[0] = isPrime[1] = False
for i in range(2, 1000001):
    if not isPrime[i]: continue
    for j in range(i * i, 1000001, i):
        isPrime[j] = False

def isSangGeunPrime(n):
    return isPrime[n] and isSangGeunNumber(n)

n = int(input())

for i in range(n):
    usedNums = set()
    if isSangGeunPrime(i):
        print(i)