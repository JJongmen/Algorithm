import sys

while True:
    line = sys.stdin.readline().rstrip()
    if line == ".":
        exit()
    stack = []
    for ch in line:
        if ch == '(' or ch == '[':
            stack.append(ch)
        elif ch == ')' or ch == ']':
            if not stack:
                stack.append(ch)
            else:
                last = stack[-1]
                if ch == ')' and last == '(':
                    stack.pop()
                elif ch == ']' and last == '[':
                    stack.pop()
                else:
                    stack.append(ch)
    if stack:
        print("no")
    else:
        print("yes")
