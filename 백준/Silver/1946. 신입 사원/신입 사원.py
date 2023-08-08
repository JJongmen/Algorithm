import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N = int(sys.stdin.readline())
    applicants = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)] # (서류 순위, 면접 순위)
    applicants.sort(key=lambda x: x[0])

    maxRank = N + 1
    result = N
    for i in range(N):
        rank = applicants[i][1]
        if maxRank < rank:
            result -= 1
        else:
            maxRank = rank
    print(result)