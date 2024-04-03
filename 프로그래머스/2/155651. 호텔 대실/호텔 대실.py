from heapq import *

def solution(book_time):
    answer = 0
    book_time.sort()
    pq = []
    def toMinutes(HHMM):
        return int(HHMM[:2]) * 60 + int(HHMM[3:])
    for start, end in book_time:
        if not pq:
            heappush(pq, toMinutes(end))
            answer += 1
            continue
        while pq and toMinutes(start) >= pq[0] + 10:
            heappop(pq)
        heappush(pq, toMinutes(end))
        answer = max(answer, len(pq))
    return answer