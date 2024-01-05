class Solution {
    fun solution(array: IntArray): IntArray {
        val max = array.maxOrNull()!!
        return intArrayOf(max, array.indexOf(max)!!)
    }
}