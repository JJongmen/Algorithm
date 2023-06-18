def gcd(x, y):
    if y == 0:
        return x
    return gcd(y, x % y)

def lcm(x, y):
    return x * y / gcd(x, y)

A, B = map(int, input().split())
A2, B2 = map(int, input().split())
Y = int(lcm(B, B2))
X = int(A * Y / B + A2 * Y / B2)
gcd_num = gcd(X, Y)
X, Y = int(X / gcd_num), int(Y / gcd_num)
print(X, Y)