class Solution {
    fun solution(s: String): Int {
        var nums = mutableListOf<Int>()
        s.split(" ").forEach {
            if (it == "Z") {
                nums.removeLast()
            } else {
                nums.add(it.toInt())
            }
        }
        return nums.sum()
    }
}