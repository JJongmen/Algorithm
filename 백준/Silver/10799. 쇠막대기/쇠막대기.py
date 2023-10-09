import sys

expr = sys.stdin.readline().rstrip()
prev = None
result = 0

stack = []
for ch in expr:
    if ch == '(':
        stack.append(ch)
    else:
        # 레이저
        if prev == '(':
            stack.pop()
            result += len(stack)
        else:
            stack.pop()
            result += 1
    prev = ch
print(result)