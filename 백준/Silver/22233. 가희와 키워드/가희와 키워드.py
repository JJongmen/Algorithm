import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
notepad = set([sys.stdin.readline().rstrip() for _ in range(N)])
for _ in range(M):
    keywords = sys.stdin.readline().rstrip().split(',')
    notepad.difference_update(keywords)
    print(len(notepad))