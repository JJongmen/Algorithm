wheels = [[]] + list(list(map(int, input())) for _ in range(4))
K = int(input())
rotates = [list(map(int, input().split())) for _ in range(K)]

rotate = {
    -1: lambda x: x[1:] + [x[0]],   # 반시계 방향 회전
    0: lambda x: x,                 # 그대로
    1: lambda x: [x[7]] + x[:7]     # 시계 방향 회전
}

for wheel_num, d in rotates:
    rotate_marking = [0] * 5
    rotate_marking[wheel_num] = d
    idx = wheel_num + 1
    while idx <= 4 and wheels[idx - 1][2] != wheels[idx][-2]:
        rotate_marking[idx] = -rotate_marking[idx - 1]
        idx += 1
    idx = wheel_num - 1
    while idx >= 1 and wheels[idx][2] != wheels[idx + 1][-2]:
        rotate_marking[idx] = -rotate_marking[idx + 1]
        idx -= 1

    for i in range(1, 5):
        wheels[i] = rotate[rotate_marking[i]](wheels[i])

score = 0
for i in range(1, 5):
    score += 2 ** (i - 1) if wheels[i][0] == 1 else 0
print(score)