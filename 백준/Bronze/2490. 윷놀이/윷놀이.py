tup = ("D", "C", "B", "A", "E")

for i in range(0, 3):
    a, b, c, d = map(int, input().split())
    cnt = a + b + c + d
    print(tup[cnt])