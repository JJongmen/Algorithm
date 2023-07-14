import sys
import collections

N, M = map(int, sys.stdin.readline().rstrip().split())
words = [sys.stdin.readline().rstrip() for _ in range(N)]
words = [word for word in words if len(word) >= M]
count_dict = collections.Counter(words)
print(*sorted(count_dict, key=lambda x: (-count_dict[x], -len(x), x)), sep='\n')