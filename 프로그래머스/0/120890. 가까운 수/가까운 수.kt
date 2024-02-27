import kotlin.math.abs;

class Solution {
    fun solution(array: IntArray, n: Int): Int {
        var answer: Int = 0
        var minDiff: Int = 10000
        array.sorted().forEach { num ->
            val diff = abs(num - n)
            if (diff < minDiff) {
                answer = num
                minDiff = diff
            }
        }
        return answer
    }
}