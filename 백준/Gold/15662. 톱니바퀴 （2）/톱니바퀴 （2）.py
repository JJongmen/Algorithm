import sys

input = sys.stdin.readline

T = int(input())
chains = [list(map(int, ' '.join(input().rstrip()).split())) for _ in range(T)]
K = int(input())
for _ in range(K):
    num, direction = map(int, input().rstrip().split())
    num -= 1
    # 회전할 체인과 방향을 저장
    to_rotate = [(num, direction)]
    # 왼쪽 체인
    tmp_d = direction
    for i in range(num - 1, -1, -1):
        if chains[i][2] != chains[i + 1][6]:
            to_rotate.append((i, -tmp_d))
            tmp_d = -tmp_d
        else:
            break
    # 오른쪽 체인
    tmp_d = direction
    for i in range(num + 1, T):
        if chains[i][6] != chains[i - 1][2]:
            to_rotate.append((i, -tmp_d))
            tmp_d = -tmp_d
        else:
            break
    # 회전
    for chain, d in to_rotate:
        if d == 1:
            chains[chain] = [chains[chain][-1]] + chains[chain][:-1]
        else:
            chains[chain] = chains[chain][1:] + [chains[chain][0]]
# 12시방향이 S극인 톱니바퀴 개수 구하기
result = 0
for i in range(T):
    if chains[i][0] == 1:
        result += 1
print(result)