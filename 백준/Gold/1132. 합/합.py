import sys

def i2c(i):
    return chr(i + ord('A'))

N = int(sys.stdin.readline())
alphabet_dict = {i2c(i):0 for i in range(10)}
alphabet_set = set()
non_zeros_set = set()
for _ in range(N):
    numStr = sys.stdin.readline().rstrip()
    for i in range(len(numStr)):
        alphabet_dict[numStr[-1-i]] += 10 ** i
        alphabet_set.add(numStr[-1-i])
    non_zeros_set.add(numStr[0])
alphabet_dict = [(k, v) for k, v in alphabet_dict.items() if k in alphabet_set]
alphabet_dict.sort(key=lambda x: x[1])
if len(alphabet_set) == 10 and len(non_zeros_set) > 0:
    for (k, v) in alphabet_dict:
        if not k in non_zeros_set:
            alphabet_dict.remove((k, v))
            alphabet_dict.insert(0, (k, v))
            break
result = 0
for i in range(len(alphabet_dict)):
    result += (9 - i) * alphabet_dict[-1-i][1]
print(result)