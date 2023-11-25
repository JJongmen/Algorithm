n = int(input())

fib = [0] * 1_000_001
fib[1] = 1
for i in range(2, n + 1):
    fib[i] = fib[i - 1] + fib[i - 2]
    fib[i] %= 1_000_000_007
print(fib[n])