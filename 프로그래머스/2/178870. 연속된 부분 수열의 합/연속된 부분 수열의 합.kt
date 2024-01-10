class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var answer: IntArray = intArrayOf()
        var curLen = 1_000_005
        var (l, r) = 0 to 0
        var sum = sequence[0]
        while (l < sequence.size && r < sequence.size) {
            if (sum < k) {
                if (r == sequence.lastIndex) break
                sum += sequence[++r]
            } else if (sum > k) {
                sum -= sequence[l++]
            } else {
                if (r - l < curLen) {
                    curLen = r - l
                    answer = intArrayOf(l, r)
                }
                if (r == sequence.lastIndex) break
                sum += sequence[++r]
                sum -= sequence[l++]
            }
        }
        return answer
    }
}