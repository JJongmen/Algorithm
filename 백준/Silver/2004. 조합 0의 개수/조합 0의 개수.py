N, M = map(int, input().split())


def factorization_k(num, k):
    cnt_k = 0
    while num > 0:
        cnt_k += num // k
        num //= k
    return cnt_k


cnt_2 = factorization_k(N, 2) - factorization_k(N - M, 2) - factorization_k(M, 2)
cnt_5 = factorization_k(N, 5) - factorization_k(N - M, 5) - factorization_k(M, 5)
print(min(cnt_2, cnt_5))