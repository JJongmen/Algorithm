import sys

N = int(sys.stdin.readline())

for cnt5 in range(N // 5, -1, -1):
    if (N - cnt5 * 5) % 2 == 0:
        cnt2 = (N - cnt5 * 5) // 2
        print(cnt5 + cnt2)
        exit(0)
    else:
        continue
print(-1)