import sys

board = [list(map(int, input().split())) for _ in range(5)]

numbers = set()

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 재귀 함수를 통해 모든 경우의 수를 탐색
def dfs(x, y, cnt, num):
    # 현재 위치의 숫자를 num에 추가
    num += str(board[x][y])
    
    # 6자리 수가 완성되면 numbers에 추가하고 종료
    if cnt == 6:
        numbers.add(num)
        return
    
    # 상하좌우로 이동하며 재귀 호출
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        # 범위를 벗어나지 않고, 방문하지 않은 숫자라면 재귀 호출
        if 0 <= nx < 5 and 0 <= ny < 5:
            dfs(nx, ny, cnt+1, num)
            
# 모든 위치에서 dfs 함수 호출
for i in range(5):
    for j in range(5):
        dfs(i, j, 1, "")

# numbers의 길이를 출력
print(len(numbers))