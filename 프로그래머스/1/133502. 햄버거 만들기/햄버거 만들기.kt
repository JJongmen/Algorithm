class Solution {
    fun solution(ingredient: IntArray): Int {
        var answer: Int = 0
        val order = listOf(1, 2, 3, 1)
        val list = mutableListOf<Int>()
        ingredient.forEach {
            list.add(it)
            while (list.takeLast(4) == order) {
                repeat(4) { list.removeLast() }
                answer++
            }
        }
        return answer
    }
}