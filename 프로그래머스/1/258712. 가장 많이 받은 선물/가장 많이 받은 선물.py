def solution(friends, gifts):
    # friend to index 딕셔너리 생성
    N = len(friends)
    f2i = {}
    for i in range(N):
        f2i[friends[i]] = i
    
    # 기록 테이블 생성
    log = [[0] * (N + 1) for _ in range(N + 1)]
    for gift in gifts:
        A, B = gift.split()
        aIdx, bIdx = f2i[A], f2i[B]
        log[aIdx][bIdx] += 1 # A -> B 개수 + 1
        log[aIdx][N] += 1 # 총 준 개수 + 1
        log[N][bIdx] += 1 # 총 받은 개수 + 1
    
    receive = [0] * N
    for i in range(N - 1):
        for j in range(i, N):
            if log[i][j] > log[j][i]:
                receive[i] += 1
            elif log[i][j] < log[j][i]:
                receive[j] += 1
            else:
                iScore = log[i][N] - log[N][i]
                jScore = log[j][N] - log[N][j]
                if iScore > jScore:
                    receive[i] += 1
                elif iScore < jScore:
                    receive[j] += 1
    return max(receive)