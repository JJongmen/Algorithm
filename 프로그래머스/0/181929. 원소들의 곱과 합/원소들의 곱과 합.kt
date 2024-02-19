class Solution {
    fun solution(num_list: IntArray): Int {
        val prefixProduct = num_list.reduce { total, cur -> total * cur }
        val prefixSum = num_list.reduce { total, cur -> total + cur }
        return if (prefixProduct < prefixSum * prefixSum) 1 else 0
    }
}