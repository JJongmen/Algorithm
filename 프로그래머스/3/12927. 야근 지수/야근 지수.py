from heapq import *

def solution(n, works):
    answer = 0
    q = []
    for work in works:
        heappush(q, (-work, work))
    while n > 0:
        max_ = heappop(q)[1]
        if max_ == 0: break
        remain = max_ - 1
        heappush(q, (-remain, remain))
        n -= 1
        
    for num in q:
        answer += num[1] * num[1]
    return answer