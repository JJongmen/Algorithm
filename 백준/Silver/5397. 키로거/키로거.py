import sys

T = int(sys.stdin.readline())
for _ in range(T):
    left = []
    right = []
    cmd = list(sys.stdin.readline().rstrip())
    for ch in cmd:
        if ch == '<':
            if left:
                right.append(left.pop())
        elif ch == '>':
            if right:
                left.append(right.pop())
        elif ch == '-':
            if left:
                left.pop()
        else:
            left.append(ch)
    left.extend(reversed(right))
    print(''.join(left))
