import sys

N = int(sys.stdin.readline())
last = 0
stack = []
result = []
for _ in range(N):
    num = int(sys.stdin.readline())
    if last < num:
        for i in range(last + 1, num + 1):
            stack.append(i)
            result.append('+')
        stack.pop()
        result.append('-')
        last = num
    else:
        if stack[-1] == num:
            stack.pop()
            result.append('-')
        else:
            print("NO")
            exit()
print('\n'.join(result))