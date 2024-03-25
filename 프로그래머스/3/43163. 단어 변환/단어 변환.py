from collections import Counter

def solution(begin, target, words):
    answer = 0
    # target이 words 안에 없으면 변환 불가능
    if target not in words:
        return 0
    
    dict = {word:[] for word in words}
    dict[begin] = []
    def canTransfer(word, other):
        a = Counter(word)
        b = Counter(other)
        c = a | b
        if sum((c - a).values()) == 1 and sum((c - b).values()) == 1:
            return True
        return False
    for idx, word in enumerate(words):
        if canTransfer(begin, word):
            dict[begin].append(word)
        for other in words[idx + 1:]:
            if canTransfer(word, other):
                dict[word].append(other)
                dict[other].append(word)
    
    visit = {word:False for word in words}
    q = [(begin, 0)]
    visit[begin] = True
    while q:
        word, cnt = q.pop(0)
        if word == target:
            return cnt
        for nxt in dict[word]:
            if visit[nxt]: continue
            q.append((nxt, cnt + 1))
            visit[nxt] = True
    return 0