class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        var totalReceiveCnt: Int = 0
        var shopRemainCnt = n
        while (shopRemainCnt / a > 0) {
            val curReceiveCnt = shopRemainCnt / a * b
            totalReceiveCnt += curReceiveCnt
            shopRemainCnt = curReceiveCnt + shopRemainCnt % a
        }
        return totalReceiveCnt
    }
}