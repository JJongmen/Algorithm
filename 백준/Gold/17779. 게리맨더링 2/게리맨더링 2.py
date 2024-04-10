N = int(input())
board = [[0]] + [list(map(int, [0] + input().split())) for _ in range(N)]

total_population = sum(map(sum, board))
answer = float('inf')
def solve(x, y, d1, d2):
    populations = [0] * 5
    # 1번 선거구
    for r in range(1, x + d1):
        for c in range(1, y + 1):
            if r + c < x + y:
                populations[1] += board[r][c]
    # 2번 선거구
    for r in range(1, x + d2 + 1):
        for c in range(y + 1, N + 1):
            if c - r > y - x:
                populations[2] += board[r][c]
    # 3번 선거구
    for r in range(x + d1, N + 1):
        for c in range(1, y - d1 + d2):
            if r - c > x - y + 2 * d1:
                populations[3] += board[r][c]
    # 4번 선거구
    for r in range(x + d2 + 1, N + 1):
        for c in range(y - d1 + d2, N + 1):
            if r + c > x + y + 2 * d2:
                populations[4] += board[r][c]
    # 5번 선거구
    populations[0] = total_population - sum(populations[1:])
    global answer
    answer = min(answer, max(populations) - min(populations))

for x in range(1, N + 1):
    for y in range(1, N + 1):
        for d1 in range(1, N + 1):
            for d2 in range(1, N + 1):
                if x + d1 + d2 > N: continue
                if 1 > y - d1: continue
                if y + d2 > N: continue
                solve(x, y, d1, d2)
print(answer)