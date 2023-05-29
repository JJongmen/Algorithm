import sys

left = list(sys.stdin.readline().rstrip())
right = []
M = int(sys.stdin.readline())
for _ in range(M):
    commands = list(sys.stdin.readline().split())
    if commands[0] == 'L' and left:
        right.append(left.pop())
    elif commands[0] == 'D' and right:
        left.append(right.pop())
    elif commands[0] == 'B' and left:
        left.pop()
    elif commands[0] == 'P':
        left.append(commands[1])

left.extend(reversed(right))
print(''.join(left))