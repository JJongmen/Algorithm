class Solution {
    fun solution(my_string: String, queries: Array<IntArray>): String {
        var answer = my_string
        queries.forEach {
            answer = answer.slice(0 until it[0]) + answer.slice(it[0]..it[1]).reversed() + answer.slice(it[1] + 1 until my_string.length)
        }
        return answer
    }
}