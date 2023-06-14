import sys

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

N = int(sys.stdin.readline())
radiuses = list(map(int, sys.stdin.readline().rstrip().split()))
for i in range(1, N):
    k = gcd(radiuses[0], radiuses[i])
    print(str(int(radiuses[0] / k)) + "/" + str(int(radiuses[i] / k)))
