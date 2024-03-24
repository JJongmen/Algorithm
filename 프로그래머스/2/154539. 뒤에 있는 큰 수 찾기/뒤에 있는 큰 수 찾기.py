def solution(numbers):
    answer = [0] * len(numbers)
    st = []
    for i in range(len(numbers) - 1, -1, -1):
        while st and numbers[i] >= st[len(st) - 1]:
            st.pop()
        if st:
            answer[i] = st[len(st) - 1]
        else:
            answer[i] = -1
        st.append(numbers[i])
    return answer