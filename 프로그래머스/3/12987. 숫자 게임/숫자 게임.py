def solution(A, B):    
    answer, a, b, L = 0, 0, 0, len(A)
    A.sort()
    B.sort()
    while a < L:
        while b < L and A[a] >= B[b]:
            b += 1
        if b == L: break
        a += 1
        b += 1
        answer += 1
    return answer