def solution(picture, k):
    answer = []
    for a in picture:
        row = ""
        for b in a:
            row += b * k
        answer.extend([row] * k)
    return answer