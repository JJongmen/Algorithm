class Solution {
    fun solution(orders: Array<String>): Int {
        var answer: Int = 0
        orders.forEach { order ->
            if ("americano" in order || "anything" in order) answer += 4500
            else if ("cafelatte" in order) answer += 5000
        }
        return answer
    }
}