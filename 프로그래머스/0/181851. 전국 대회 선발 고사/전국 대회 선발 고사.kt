class Solution {
    fun solution(rank: IntArray, attendance: BooleanArray): Int {
        val ranking = rank.withIndex().filter { (index, _) -> attendance[index] }.sortedBy { it.value }
        return 10000 * ranking[0].index + 100 * ranking[1].index + ranking[2].index
    }
}