import sys

T = int(sys.stdin.readline())
for _ in range(T):
    sounds = list(sys.stdin.readline().rstrip().split())
    removeSounds = []
    while True:
        sound = list(sys.stdin.readline().rstrip().split())
        if len(sound) == 5: break
        removeSounds.append(sound[2])
    print(*[i for i in sounds if i not in removeSounds])