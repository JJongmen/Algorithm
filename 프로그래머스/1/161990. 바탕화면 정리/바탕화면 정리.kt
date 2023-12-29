class Solution {
    fun solution(wallpapers: Array<String>): IntArray {
        var answer: IntArray = intArrayOf(MAX, MAX, MIN, MIN)
        wallpapers.forEachIndexed { row, wallpaper ->
            wallpaper.forEachIndexed { col, state ->
                if (state == '#') {
                    answer[0] = minOf(answer[0], row)
                    answer[1] = minOf(answer[1], col)
                    answer[2] = maxOf(answer[2], row + 1)
                    answer[3] = maxOf(answer[3], col + 1)
                }
            }
        }
        return answer
    }
    
    companion object {
        private const val MIN = -1
        private const val MAX = 100
    }
}