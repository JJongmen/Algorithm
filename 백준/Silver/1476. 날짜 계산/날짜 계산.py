E, S, M = map(int, input().split())

while True:
    if S % 15 == E % 15 and S % 19 == M % 19:
        break
    S += 28
print(S)