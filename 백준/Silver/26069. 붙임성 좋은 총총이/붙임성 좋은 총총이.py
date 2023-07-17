import sys

dancing = set()
dancing.add("ChongChong")
N = int(sys.stdin.readline())
for _ in range(N):
    A, B = sys.stdin.readline().rstrip().split()
    if A in dancing:
        dancing.add(B)
    if B in dancing:
        dancing.add(A)
print(len(dancing))