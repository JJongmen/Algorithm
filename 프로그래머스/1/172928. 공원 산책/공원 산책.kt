class Solution {
    private val dMap = mapOf("E" to 0, "S" to 1, "W" to 2, "N" to 3)
    private val dx = listOf(0, 1, 0, -1)
    private val dy = listOf(1, 0, -1, 0)
    
    fun findStart(park: Array<String>): Pair<Int, Int> {
        val x = park.indexOfFirst { it.contains('S') }
        return x to park[x].indexOfFirst { it == 'S' }
    }
    
    fun isOut(x: Int, y: Int, park: Array<String>): Boolean {
        return x !in 0 until park.size || y !in 0 until park[0].length
    }
    
    fun isBlocked(x: Int, y: Int, d: Int, dist: Int, park: Array<String>): Boolean {
        for (i in 1..dist) {
            val nx = x + dx[d] * i
            val ny = y + dy[d] * i
            if (park[nx][ny] == 'X') return true
        }
        return false
    }
    
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        var (x, y) = findStart(park)
        routes.forEach { route ->
            val (dStr, distStr) = route.split(" ")
            val (d, dist) = dMap[dStr]!! to distStr.toInt()
            val (nx, ny) = x + dx[d] * dist to y + dy[d] * dist
            if (isOut(nx, ny, park) || isBlocked(x, y, d, dist, park)) return@forEach
            x = nx.also { y = ny }
        }
        return intArrayOf(x, y)
    }
}