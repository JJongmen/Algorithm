cards = list(range(0, 21))

for _ in range(10):
    a, b = map(int, input().split())
    # cards[a:(b + 1)] = cards[a:(b + 1)].reverse()
    cards[a:(b + 1)] = list(reversed(cards[a:(b + 1)]))

print(*cards[1:], sep=' ')