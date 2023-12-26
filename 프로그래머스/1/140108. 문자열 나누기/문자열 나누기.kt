class Solution {
    fun solution(s: String): Int {
        var answer = 0
        val stack = mutableListOf<Char>()
        s.forEach {
            if (stack.isEmpty()) answer++
            if (stack.isEmpty() || stack.last() == it) {
                stack.add(it)
            } else {
                stack.removeLast()
            }
        }
        return answer
    }
}