N = input()
if sum(map(int, N)) % 3 == 0 and N.count('0') > 0:
    print(*sorted(N, reverse=True), sep='')
else:
    print(-1)