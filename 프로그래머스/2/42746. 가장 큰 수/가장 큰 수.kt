class Solution {
    fun solution(numbers: IntArray): String {
        val answer = numbers.sortedWith(Comparator({num1: Int, num2: Int ->
            "$num2$num1".compareTo("$num1$num2")
        })).joinToString("")
        if (answer[0] == '0') {
            return "0"
        }
        return answer
    }
}
