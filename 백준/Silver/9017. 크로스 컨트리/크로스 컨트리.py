# 한 팀은 여섯 명의 선수
# 팀 점수는 상위 네 명의 주자의 점수의 합
# 가장 낮은 점수를 얻는 팀 우승
# 여섯 명보다 적으면 점수 계산 제외
# 동점이면 다섯 번째 주자가 가장 빨리 들어온 팀이 우승

import collections
import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N = int(sys.stdin.readline())
    order = list(map(int, sys.stdin.readline().rstrip().split()))
    teams = collections.Counter(order)
    team_info = [[0] * 3 for _ in range(201)] # [점수, 완주 인원, 5번째 주자]
    rank = 1
    for i in range(N):
        team = order[i]
        if teams[team] < 6: continue
        team_info[team][1] += 1
        if team_info[team][1] <= 4:
            team_info[team][0] += rank
        elif team_info[team][1] == 5:
            team_info[team][2] = rank
        rank += 1
    
    winner = 0
    winner_score = float('inf')
    for team in range(1, 201):
        if teams[team] < 6: continue
        if team_info[team][0] < winner_score:
            winner_score = team_info[team][0]
            winner = team
        elif team_info[team][0] == winner_score:
            if team_info[team][2] < team_info[winner][2]:
                winner = team
    print(winner)