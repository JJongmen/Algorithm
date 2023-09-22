import sys
from collections import deque

N = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().rstrip().split()))   # 사용한 기술 순서

hand = deque()
for card_num, skill_num in enumerate(reversed(A)):
    card_num += 1
    if skill_num == 1:
        hand.appendleft(card_num)
    elif skill_num == 2:
        hand.insert(1, card_num)
    elif skill_num == 3:
        hand.append(card_num)
print(*hand)