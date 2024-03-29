def solution(arr):
    def compressure(x, y, L):
        target = arr[x][y]
        result = [0, 0]
        for i in range(x, x + L):
            for j in range(y, y + L):
                if arr[i][j] == target: continue
                result = [i + j for i, j in zip(result, compressure(x, y, L // 2))]
                result = [i + j for i, j in zip(result, compressure(x, y + L // 2, L // 2))]
                result = [i + j for i, j in zip(result, compressure(x + L // 2, y, L // 2))]
                return [i + j for i, j in zip(result, compressure(x + L // 2, y + L // 2, L // 2))]
        return [1, 0] if target == 0 else [0, 1]
    return compressure(0, 0, len(arr))