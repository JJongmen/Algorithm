class Solution {
    fun solution(n: Int): IntArray {
        val triangle = MutableList(n) { MutableList(it + 1) { 0 } }
        val (dx, dy) = listOf(1, 0, -1) to listOf(0, 1, -1)
        var (x, y) = -1 to 0
        var d = 0
        var num = 1
        (n downTo 1).forEach {
            repeat(it) {
                x += dx[d]
                y += dy[d]
                triangle[x][y] = num++
            }
            d = (d + 1) % 3
        }
        return triangle.flatten().toIntArray()
    }
}