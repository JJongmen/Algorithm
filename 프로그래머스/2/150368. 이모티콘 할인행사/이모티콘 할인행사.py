from itertools import *

def solution(users, emoticons):
    answer = [0, 0]
    # 1. 가능한 할인율 경우의 수 계산
    percentages = list(product([10, 20, 30, 40], repeat = len(emoticons)))
    
    # 2. 각 할인율마다의 이모티콘 플러스 서비스 가입자 수 및 이모티콘 판매액 계산
    for percentage in percentages:
        plus_cnt = 0
        sell_price = 0
        # 3. 각 회원마다 계산
        for rate, price in users:
            sum_price = 0
            for i in range(len(emoticons)):
                if rate <= percentage[i]:
                    sum_price += emoticons[i] * (100 - percentage[i]) // 100
            if sum_price >= price:
                plus_cnt += 1
            else:
                sell_price += sum_price
        if answer[0] < plus_cnt:
            answer = [plus_cnt, sell_price]
        elif answer[0] == plus_cnt:
            answer[1] = max(answer[1], sell_price)
    return answer