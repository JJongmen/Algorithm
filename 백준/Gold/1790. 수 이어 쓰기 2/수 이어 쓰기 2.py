import sys

n, k = map(int, sys.stdin.readline().split())

last_num = 0
num_len = 1
num_count = 9

while k > num_len * num_count:
    k -= num_len * num_count
    last_num += num_count
    num_len += 1
    num_count = num_count * 10

last_num = (last_num + 1) + ((k-1) // num_len)

if last_num > n:
    print(-1)
    exit()
print(str(last_num)[(k-1) % num_len])