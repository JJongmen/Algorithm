import sys

N1, N2 = map(int, sys.stdin.readline().rstrip().split())
group1 = sys.stdin.readline().rstrip()
group2 = sys.stdin.readline().rstrip()
T = int(sys.stdin.readline().rstrip())

# 개미의 순서를 반대로 뒤집어서 합침
ants = list(group1[::-1] + group2)
for _ in range(T):
    # 점프할 개미를 찾아서 위치를 바꿈
    i = 0
    while i < N1 + N2 - 1:
        if ants[i] in group1 and ants[i + 1] in group2:
            ants[i], ants[i + 1] = ants[i + 1], ants[i]
            i += 2
        else:
            i += 1

print(''.join(ants))