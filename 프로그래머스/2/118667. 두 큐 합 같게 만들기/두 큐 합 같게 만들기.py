def solution(queue1, queue2):
    answer = 0
    link = queue1 + queue2
    if sum(link) % 2 == 1:
        return -1
    l, r = 0, len(queue1) - 1
    cur, target = sum(queue1), sum(link) // 2
    while l <= r:
        if cur < target:
            r += 1
            if r == len(link): return -1
            cur += link[r]
        elif cur > target:
            if l == r: return -1
            cur -= link[l]
            l += 1
        else: break
        answer += 1
    return answer