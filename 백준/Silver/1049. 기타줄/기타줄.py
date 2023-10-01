import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
package_min, piece_min = 1001, 1001
for _ in range(M):
    package, piece = map(int, sys.stdin.readline().rstrip().split())
    package_min = min(package_min, package)
    piece_min = min(piece_min, piece)
if package_min >= piece_min * 6:
    print(N * piece_min)
else:
    if package_min <= (N % 6) * piece_min:
        print(N // 6 * package_min + package_min)
    else:
        print(N // 6 * package_min + (N % 6) * piece_min)