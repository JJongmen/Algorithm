class Solution {
    fun solution(array: IntArray): Int {
        var answer: Int = 0
        array.forEach { num ->
            answer += num.toString().count { it == '7' }
        }
        return answer
    }
}