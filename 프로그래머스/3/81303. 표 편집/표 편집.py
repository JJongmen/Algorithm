def solution(n, k, cmd):
    answer = ''
    cur = k
    board = [[True, i - 1, i + 1] for i in range(n)]
    restores = []
    
    def C():
        restores.append(cur)
        board[cur][0] = False
        prev = board[cur][1]
        nxt = board[cur][2]
        
        if prev >= 0:
            board[prev][2] = board[cur][2]
        if nxt < n:
            board[nxt][1] = board[cur][1]
        if nxt == n:
            return prev
        return nxt
    def Z():
        restore = restores.pop()
        prev = board[restore][1]
        nxt = board[restore][2]
        board[restore][0] = True
        if prev >= 0:
            board[prev][2] = restore
        if nxt < n:
            board[nxt][1] = restore
        return cur
    def U(X):
        nxt = cur
        for _ in range(X):
            nxt = board[nxt][1]
        return nxt
    def D(X):
        nxt = cur
        for _ in range(X):
            nxt = board[nxt][2]
        return nxt
    for c in cmd:
        if c == "C":
            cur = C()
        elif c == "Z":
            cur = Z()
        else:
            a, X = c.split()
            X = int(X)
            if a == "U":
                cur = U(X)
            else:
                cur = D(X)
                
    for i in range(n):
        board[i] = 'O' if board[i][0] == 1 else 'X'
    return "".join(board)