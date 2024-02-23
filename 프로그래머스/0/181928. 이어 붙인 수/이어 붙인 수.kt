class Solution {
    fun solution(num_list: IntArray) =
        num_list.filter { it % 2 == 0 }.reduce { total, cur -> total * 10 + cur } +
        num_list.filter { it % 2 != 0 }.reduce { total, cur -> total * 10 + cur }
}