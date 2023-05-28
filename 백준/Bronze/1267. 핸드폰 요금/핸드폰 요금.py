N = int(input())

times = list(map(int, input().split()))

Y, M = 0, 0
for time in times:
    Y += 10 * int(time / 30 + 1)
    M += 15 * int(time / 60 + 1)

if Y < M:
    print(f"Y {Y}")
elif Y > M:
    print(f"M {M}")
else:
    print(f"Y M {Y}")