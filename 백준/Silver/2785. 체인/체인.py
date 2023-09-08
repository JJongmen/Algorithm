import sys

N = int(sys.stdin.readline())
chain_lengths = list(map(int, sys.stdin.readline().rstrip().split()))
chain_lengths.sort()

result = 0
while len(chain_lengths) > 1:
    result += 1
    chain_lengths[0] -= 1
    if chain_lengths[0] == 0:
        chain_lengths.pop(0)
    if len(chain_lengths) == 1:
        break
    chain_lengths[-2] += chain_lengths[-1]
    chain_lengths.pop()
print(result)