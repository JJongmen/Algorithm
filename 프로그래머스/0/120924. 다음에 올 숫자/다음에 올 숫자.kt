class Solution {
    fun solution(common: IntArray): Int {
        if (common[2] - common[1] == common[1] - common[0]) {
            return common[common.lastIndex] + common[1] - common[0]
        }
        return common[common.lastIndex] * common[1] / common[0]
    }
}