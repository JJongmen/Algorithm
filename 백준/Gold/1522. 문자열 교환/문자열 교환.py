import sys

input = sys.stdin.readline

str_ = input().rstrip()
window_size = str_.count('a')
str_ += str_[:-1]

cur_a = str_[:window_size].count('a')
cur_b = window_size - cur_a
idx = 0
result = float('inf')
while idx < len(str_) - window_size + 1:
    result = min(result, cur_b)
    if idx == len(str_) - window_size: break
    if str_[idx] == 'a':
        cur_a -= 1
    else:
        cur_b -= 1
    if str_[idx+window_size] == 'a':
        cur_a += 1
    else:
        cur_b += 1
    idx += 1
print(result)