def kantore(n):
    if n == 0:
        return "-"
    return kantore(n - 1) + " " * 3 ** (n - 1) + kantore(n - 1)

while True:
    try:
        N = int(input())
        print(kantore(N))
    except EOFError:
        break