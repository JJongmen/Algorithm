def solution(gems):
    answer = [1, len(gems)]
    k = len(set(gems))
    l, r = 0, 0
    counter = {gems[0]:1}
    cnt = 1
    while l <= r and r < len(gems):
        if cnt < k:
            r += 1
            if r == len(gems):
                break
            gem = gems[r]
            if gem in counter:
                counter[gem] += 1
            else:
                counter[gem] = 1
                cnt += 1
        else:
            # 정답 갱신
            if answer[1] - answer[0] > r - l:
                answer = [l + 1, r + 1]
            
            gem = gems[l]
            if counter[gem] == 1:
                del counter[gem]
                cnt -= 1
            else:
                counter[gem] -= 1
            l += 1
        
    return answer