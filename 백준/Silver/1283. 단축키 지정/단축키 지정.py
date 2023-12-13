import sys

input = sys.stdin.readline

N = int(input())
answers = []
shortcuts = set()
for _ in range(N):
    answer = input().rstrip().split()
    hasShortcut = False
    for idx, word in enumerate(answer):
        if hasShortcut: break
        first = word[0].upper()
        if first in shortcuts: continue
        shortcuts.add(first)
        hasShortcut = True
        word = '[' + word[0] + ']' + word[1:]
        answer[idx] = word
    if not hasShortcut:
        for i, word in enumerate(answer):
            for j, ch in enumerate(word):
                if ch.upper() in shortcuts: continue
                shortcuts.add(ch.upper())
                answer[i] = word[:j] + '[' + ch + ']' + word[j+1:]
                hasShortcut = True
                break
            if hasShortcut: break
    answers.append(' '.join(answer))
print(*answers, sep="\n")