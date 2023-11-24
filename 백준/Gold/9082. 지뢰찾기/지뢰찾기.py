import sys

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    up = list(map(int, ' '.join(input().rstrip()).split()))
    down = input().rstrip()
    
    cnt = 0
    for idx in range(N):
        ch = down[idx]
        if ch == '*':
            if idx > 0:
                up[idx - 1] -= 1
            up[idx] -= 1
            if idx < N - 1:
                up[idx + 1] -= 1
            cnt += 1

    for idx, ch in enumerate(down):
        if ch == '*': continue
        if idx == 0:
            if up[idx] and up[idx + 1]:
                up[idx] -= 1
                up[idx + 1] -= 1
                cnt += 1
        elif idx == N - 1:
            if up[idx] and up[idx - 1]:
                up[idx] -= 1
                up[idx - 1] -= 1
                cnt += 1
        else:
            if up[idx - 1] and up[idx] and up[idx + 1]:
                up[idx - 1] -= 1
                up[idx] -= 1
                up[idx + 1] -= 1
                cnt += 1
        
    print(cnt)
