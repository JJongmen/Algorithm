class Solution {
    fun solution(food: IntArray): String {
        return food.mapIndexed { index, amount ->
            "$index".repeat(amount / 2)
        }.joinToString("").let { "${it}0${it.reversed()}" }
    }
}