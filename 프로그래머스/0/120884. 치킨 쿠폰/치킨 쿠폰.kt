class Solution {
    fun solution(chicken: Int): Int {
        var answer: Int = 0
        var tmp = chicken
        while (tmp / 10 > 0) {
            val service = tmp / 10
            tmp = service + tmp % 10
            answer += service
        }
        return answer
    }
}