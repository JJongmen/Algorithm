import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N, M = map(int, sys.stdin.readline().rstrip().split())
    printer = list(enumerate(map(int, sys.stdin.readline().rstrip().split())))

    idx = 1
    while True:
        max_val = max([x[1] for x in printer])
        if max_val == printer[0][1]:
            if M == printer[0][0]:
                print(idx)
                break
            else:
                printer.pop(0)
                idx += 1
        else:
            printer.append(printer.pop(0))
