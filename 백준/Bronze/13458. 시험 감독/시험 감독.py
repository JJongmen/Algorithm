N = int(input())
A = list(map(int, input().split()))
B, C = map(int, input().split())

A = [max(0, a - B) for a in A]
cnt = N + sum([max(0, (a - 1)) // C + 1 if a != 0 else 0 for a in A])
print(cnt)