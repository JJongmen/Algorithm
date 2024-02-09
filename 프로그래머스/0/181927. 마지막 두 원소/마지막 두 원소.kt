class Solution {
    fun solution(num_list: IntArray): IntArray {
        val previous = num_list[num_list.lastIndex -1]
        val last = num_list.last()
        return num_list + if (previous < last) last - previous else last * 2
    }
}