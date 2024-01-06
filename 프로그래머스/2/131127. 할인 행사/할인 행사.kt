class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val wantMap = want.indices.associate { want[it] to number[it] }
        return discount.toList().windowed(10).count { discountProducts ->
            discountProducts.groupingBy { it }.eachCount() == wantMap
        }
    }
}