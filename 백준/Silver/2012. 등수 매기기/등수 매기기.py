import sys

N = int(sys.stdin.readline())
expected_ranks = [int(sys.stdin.readline()) for _ in range(N)]

expected_ranks.sort()
result = 0
for idx, expected_rank in enumerate(expected_ranks):
    result += abs(idx + 1 - expected_rank)
print(result)