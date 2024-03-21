def solution(n_str):
    for idx, ch in enumerate(n_str):
        if ch == '0': continue
        return n_str[idx:]