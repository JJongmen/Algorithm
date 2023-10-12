import sys

def isInt(x):
    return isinstance(x, int)

S = sys.stdin.readline().rstrip()

stack = []
for ch in S:
    if ch == 'x':
        stack.append(0)
        while len(stack) >= 2:
            if isInt(stack[-1]) and stack[-2] == 'g':
                stack[-1] = stack.pop() + 1
            elif len(stack) >= 3 and isInt(stack[-1]) and isInt(stack[-2]) and stack[-3] == 'f':
                stack[-1] = min(stack.pop(), stack.pop())
            else:
                break
    else:
        stack.append(ch)

if len(stack) == 1 and isInt(stack[0]):
    print(stack[0])
else:
    print(-1)