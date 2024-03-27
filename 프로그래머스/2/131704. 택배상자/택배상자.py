def solution(order):
    idx, main, sub = 0, 1, []
    L = len(order)
    while idx < L:
        num = order[idx]
        if num >= main:
            sub.extend(range(main, num))
            idx += 1
            main = num + 1
        else:
            if num == sub[-1]:
                sub.pop()
                idx += 1
            else: break
    return idx