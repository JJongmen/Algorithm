A, B = map(int, input().split())
if A > B:
    A, B = B, A
print(max(0, B - A - 1))
print(*range(A + 1, B))