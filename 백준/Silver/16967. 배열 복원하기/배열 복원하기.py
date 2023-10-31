import sys

input = sys.stdin.readline

H, W, X, Y = map(int, input().rstrip().split())
B = [list(map(int, input().rstrip().split())) for _ in range(H+X)]

for i in range(H-X):
    for j in range(W-Y):
        B[i+X][j+Y] -= B[i][j]

for i in range(H):
    for j in range(W):
        print(B[i][j], end=' ')
    print()
