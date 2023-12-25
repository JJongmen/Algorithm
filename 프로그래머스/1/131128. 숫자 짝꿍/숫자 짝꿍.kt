class Solution {
    fun solution(X: String, Y: String): String {
        val xCounts = getNumCounter(X)
        val yCounts = getNumCounter(Y)
        val minCounts = xCounts.zip(yCounts).map { (x, y) -> minOf(x, y) }.toIntArray()
        if (minCounts.maxOrNull() == 0) return "-1"
        val answer = StringBuilder()
        for (num in 9 downTo 0) {
            answer.append(num.toString().repeat(minCounts[num]))
        }
        return if (answer.startsWith("0")) "0" else answer.toString()
    }
    
    private fun getNumCounter(numStr: String): IntArray {
        val counter = IntArray(10)
        for (numChr in numStr) {
            counter[numChr - '0']++
        }
        return counter
    }
}
