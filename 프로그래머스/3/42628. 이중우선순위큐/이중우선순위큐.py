from heapq import *

def solution(operations):
    answer = []
    minHeap = []
    maxHeap = []
    for operation in operations:
        cmd, numStr = operation.split()
        num = int(numStr)
        if cmd == "I":
            heappush(minHeap, num)
            heappush(maxHeap, -num)
        else:
            if num == 1 and minHeap:
                max_ = heappop(maxHeap)
                minHeap.remove(-max_)
            elif num == -1 and minHeap:
                min_ = heappop(minHeap)
                maxHeap.remove(-min_)
    if not minHeap:
        return [0, 0]
    return [-nsmallest(1, maxHeap)[0], nsmallest(1, minHeap)[0]]
