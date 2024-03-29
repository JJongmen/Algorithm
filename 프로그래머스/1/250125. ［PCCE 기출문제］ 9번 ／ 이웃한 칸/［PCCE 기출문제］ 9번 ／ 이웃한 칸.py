def solution(board, h, w):
    n = len(board)
    count = 0
    dh, dw = (0, 1, -1, 0), (1, 0, 0, -1)
    for i in range(4):
        h_check, w_check = h + dh[i], w + dw[i]
        if h_check < 0 or h_check >= n or w_check < 0 or w_check >= n: continue
        if board[h][w] == board[h_check][w_check]: count += 1
    return count