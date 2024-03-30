from itertools import permutations

def solution(user_ids, banned_ids):
    def match(user_id, banned_id):  
        if len(user_id) != len(banned_id): return False
        for a, b in zip(user_id, banned_id):
            if a == b or b == '*': continue
            return False
        return True
    N, K = len(user_ids), len(banned_ids)
    cases = list(permutations(range(N), K))
    set_ = set()
    for case in cases:
        isAllMatch = True
        for idx, banned_id in zip(case, banned_ids):
            if match(user_ids[idx], banned_id): continue
            isAllMatch = False
            break
        if isAllMatch:
            set_.add(tuple(sorted(case)))
    return len(set_)