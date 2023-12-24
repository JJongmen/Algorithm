class Solution {
    fun solution(food: IntArray): String {
        var answer = ""
        food.forEachIndexed { index, amount ->
            answer += "$index".repeat(amount / 2)
        }
        return "${answer}0${answer.reversed()}"
    }
}