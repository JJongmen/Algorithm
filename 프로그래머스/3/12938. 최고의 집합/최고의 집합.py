def solution(n, s):
    if n > s:
        return [-1]
    x = s // n
    r = s % n
    return [x] * (n - r) + [x + 1] * r