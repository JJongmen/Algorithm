from collections import deque

def solution(begin, target, words):
    answer = 0
    # target이 words 안에 없으면 변환 불가능
    if target not in words:
        return 0
    
    dict = {word:[] for word in words}
    dict[begin] = []
    def canTransfer(word, other):
        if len(word) != len(other):
            return False
        diff = 0
        for a, b in zip(word, other):
            if a != b:
                diff += 1
                if diff > 1:
                    return False
        return True
            
    for idx, word in enumerate(words):
        if canTransfer(begin, word):
            dict[begin].append(word)
        for other in words[idx + 1:]:
            if canTransfer(word, other):
                dict[word].append(other)
                dict[other].append(word)
    
    visit = {word:False for word in words}
    q = deque([(begin, 0)])
    visit[begin] = True
    while q:
        word, cnt = q.popleft()
        if word == target:
            return cnt
        for nxt in dict[word]:
            if visit[nxt]: continue
            q.append((nxt, cnt + 1))
            visit[nxt] = True
    return 0