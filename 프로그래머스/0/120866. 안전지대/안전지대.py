def solution(board):
    n = len(board)
    safeArea = [[True] * n for _ in range(n)]
    dx = (-1, 0, 1, 1, 1, 0, -1, -1)
    dy = (1, 1, 1, 0, -1, -1, -1, 0)
    
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0: continue
            safeArea[i][j] = False
            for d in range(8):
                nx = i + dx[d]
                ny = j + dy[d]
                if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
                safeArea[nx][ny] = False
    
    answer = 0
    for i in range(n):
        for j in range(n):
            if safeArea[i][j]: answer += 1
    return answer