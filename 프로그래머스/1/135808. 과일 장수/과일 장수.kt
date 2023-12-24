class Solution {
    fun solution(k: Int, m: Int, scores: IntArray): Int {
        val descendingScores = scores.sortedByDescending { it }
        return (m - 1 until scores.size step(m)).sumOf {
            descendingScores[it] * m
        }
    }
}