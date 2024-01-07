class Solution {
    fun solution(topping: IntArray): Int {
        val leftMap = mutableMapOf<Int, Int>()
        val rightMap = topping.toList().groupingBy { it }.eachCount().toMutableMap()
        var (leftCnt, rightCnt) = 0 to rightMap.size
        return topping.indices.count {
            val type = topping[it]
            leftMap.put(type, (leftMap[type] ?: 0) + 1)
            rightMap.put(type, rightMap[type]!! - 1)
            if (leftMap[type]!! == 1) leftCnt++
            if (rightMap[type]!! == 0) rightCnt--
            leftCnt == rightCnt
        }
    }
}