import sys

str = sys.stdin.readline().rstrip()
stack = []
for ch in str:
    stack.append(ch)
    if ch == ')':
        if len(stack) > 1:
            if stack[-2] == '(':
                stack.pop()
                stack.pop()
                stack.append(2)
            elif type(stack[-2]) == int:
                if len(stack) > 2 and stack[-3] == '(':
                    stack.pop()
                    num = stack.pop()
                    stack.pop()
                    stack.append(2 * num)
                else:
                    print(0)
                    exit()
        else:
            print(0)
            exit()
    elif ch == ']':
        if len(stack) > 1:
            if stack[-2] == '[':
                stack.pop()
                stack.pop()
                stack.append(3)
            elif type(stack[-2]) == int:
                if len(stack) > 2 and stack[-3] == '[':
                    stack.pop()
                    num = stack.pop()
                    stack.pop()
                    stack.append(3 * num)
                else:
                    print(0)
                    exit()
        else:
            print(0)
            exit()
    if len(stack) > 1 and type(stack[-1]) == int and type(stack[-2]) == int:
        num = stack.pop()
        stack[-1] += num

if len(stack) == 1 and type(stack[-1]) == int:
    print(stack[-1])
else:
    print(0)