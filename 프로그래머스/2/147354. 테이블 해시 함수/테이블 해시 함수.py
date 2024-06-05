def solution(data, col, row_begin, row_end):
    answer = 0
    data.sort(key=lambda x:(x[col-1], -x[0]))
    for i in range(row_begin, row_end+1):
        answer ^= sum([x % i for x in data[i-1]])
    return answer