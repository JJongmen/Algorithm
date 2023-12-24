class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        var answer: Int = 0
        var tmp = n
        while (tmp / a > 0) {
            val receiveCnt = tmp / a * b
            answer += receiveCnt
            tmp = receiveCnt + tmp % a
        }
        return answer
    }
}