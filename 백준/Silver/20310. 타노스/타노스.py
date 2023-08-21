import sys

S = sys.stdin.readline().rstrip()

zero_cnt = S.count('0')
one_cnt = len(S) - zero_cnt

print('0' * (zero_cnt // 2) + '1' * (one_cnt // 2))