def solution(routes):
    routes.sort()
    answer = 1
    c = routes[0][1]
    for a, b in routes:
        if c < a:
            answer += 1
            c = b
        else:
            c = min(b, c)
    return answer