counts = [0 for _ in range(10)]
N = input()
for num in N:
    counts[int(num)] += 1

maxCnt = max([counts[i] for i in range(10) if i != 6 and i != 9])
maxCnt = max(maxCnt, int((counts[6] + counts[9] + 1) / 2))
print(maxCnt)