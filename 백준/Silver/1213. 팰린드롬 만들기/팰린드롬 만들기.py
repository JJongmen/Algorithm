name = input()

counts = [0] * 26
for ch in name:
    counts[ord(ch) - ord('A')] += 1

oddIdx = -1
for i in range(26):
    if counts[i] % 2 == 1:
        if (oddIdx != -1):
            print("I'm Sorry Hansoo")
            exit()
        oddIdx = i

if oddIdx != -1:
    counts[oddIdx] -= 1
ans = []
for i, cnt in enumerate(counts):
    if cnt != 0 and cnt % 2 == 0:
        ans.append(chr(ord('A') + i) * int(cnt / 2))

right = ans[::-1]
if oddIdx != -1:
    ans.append(chr(ord('A') + oddIdx))
ans.extend(right)
print(*ans, sep='')