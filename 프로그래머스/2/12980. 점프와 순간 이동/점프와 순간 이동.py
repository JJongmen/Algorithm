def solution(n):
    ans = 0
    tmp = n
    while tmp > 0:
        if tmp % 2 == 1:
            ans += 1
        tmp //= 2
    return ans