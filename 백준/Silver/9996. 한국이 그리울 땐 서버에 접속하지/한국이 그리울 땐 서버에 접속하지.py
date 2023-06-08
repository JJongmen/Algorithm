import sys
import re

N = int(sys.stdin.readline())
pattern = sys.stdin.readline().rstrip()
idx = pattern.find('*')
pattern = pattern[:idx] + '.' + pattern[idx:]
for _ in range(N):
    name = sys.stdin.readline().rstrip()
    m = re.match(pattern, name)
    if m and m.end() == len(name):
        print("DA")
    else:
        print("NE")