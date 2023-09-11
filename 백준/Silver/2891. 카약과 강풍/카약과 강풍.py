import sys

# 팀의 수, 카약이 손상된 팀의 수, 카약을 하나 더 가져온 팀의 수
N, S, R = map(int, sys.stdin.readline().rstrip().split())
# 카약이 손상된 팀
damaged = list(map(int, sys.stdin.readline().rstrip().split()))
need_extra = [False] * (N + 1)
for team in damaged:
    need_extra[team] = True
# 카약을 하나 더 가져온 팀
extra = list(map(int, sys.stdin.readline().rstrip().split()))
has_extra = [False] * (N + 1)
for team in extra:
    has_extra[team] = True

'''
카약은 자신의 바로 다음이나 바로 이전 팀에게만 빌려줄 수 있다.
다른 팀에게서 받은 카약은 또 다른 팀에게 빌려줄 수 없다.
카약을 하나 더 가져온 팀의 카약이 손상되었다면 빌려줄 수 없다.
'''

result = 0
for team in extra:
    if need_extra[team]:
        need_extra[team] = False
        has_extra[team] = False

for team in damaged:
    if not need_extra[team]: continue
    if team > 1 and has_extra[team - 1]:
        has_extra[team - 1] = False
        continue
    if team < N and has_extra[team + 1]:
        has_extra[team + 1] = False
        continue
    result += 1
print(result)