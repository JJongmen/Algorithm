import math
import sys

global K

def merge(A, p, q, r):
    global K
    i, j, t = p, q + 1, 1
    tmp = []
    while i <= q and j <= r:
        if A[i] <= A[j]:
            tmp.append(A[i])
            i += 1
        else:
            tmp.append(A[j])
            j += 1
    tmp.extend(A[i:q + 1])
    tmp.extend(A[j:r + 1])
    if K <= r - p + 1:
        print(tmp[K - 1])
        exit()
    A[p:r + 1] = tmp
    K -= r - p + 1

def merge_sort(A, p, r):
    if p < r:
        q = int(math.floor((p + r) / 2))
        merge_sort(A, p, q)
        merge_sort(A, q + 1, r)
        merge(A, p, q, r)

N, K = map(int, sys.stdin.readline().rstrip().split())
A = list(map(int, sys.stdin.readline().rstrip().split()))
merge_sort(A, 0, N - 1)

print(-1)