class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val counter = tangerine.toList().groupingBy { it }.eachCount()
        return tangerine.sortedWith(compareBy({ counter[it] }, { it })).takeLast(k).distinct().count()
    }
}