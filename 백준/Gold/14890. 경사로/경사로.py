N, L = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

def isPossible(road):
    idx = 0
    streak = 1
    while idx < len(road) - 1:
        cur, nxt = road[idx], road[idx + 1]
        if cur == nxt:
            idx += 1
            streak += 1
        elif cur + 1 == nxt:
            if streak < L: return False
            idx += 1
            streak = 1
        elif cur - 1 == nxt:
            if idx + L >= len(road): return False
            for i in range(idx + 1, idx + 1 + L):
                if road[i] != nxt: return False
            idx += L
            streak = 0
        else: return False
    return True

roads = [row for row in board] + list(map(list, zip(*board)))
print(len([0 for road in roads if isPossible(road)]))