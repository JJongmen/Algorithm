N, A, B = map(int, input().split())
A, B = min(A, B), max(A, B)
round = 1
while A % 2 != 1 or B - A != 1:
    A = int((A + 1) / 2)
    B = int((B + 1) / 2)
    round += 1
print(round)