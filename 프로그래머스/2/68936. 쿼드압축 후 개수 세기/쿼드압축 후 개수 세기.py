def solution(arr):
    def compressure(x, y, L):
        target = arr[x][y]
        result = [0, 0]
        for i in range(x, x + L):
            for j in range(y, y + L):
                if arr[i][j] == target: continue
                return [sum(values) for values in zip(compressure(x, y, L // 2), compressure(x, y + L // 2, L // 2), compressure(x + L // 2, y, L // 2), compressure(x + L // 2, y + L // 2, L // 2))]
        return [1, 0] if target == 0 else [0, 1]
    return compressure(0, 0, len(arr))