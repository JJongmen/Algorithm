def solution(n, stations, w):
    answer, x, L = 0, 1, 1 + 2 * w
    for station in stations:
        y = station - w
        answer += (y - x - 1) // L + 1
        x = station + w + 1
    if x <= n:
        answer += (n - x) // L + 1
    return answer