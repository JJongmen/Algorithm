def solution(a):
    def get_min(b):
        balloons = []
        min_ = 1000000001
        for num in b:
            if min_ > num:
                min_ = num
                balloons.append(num)
        return balloons
    return len(set(get_min(a) + get_min(a[::-1])))