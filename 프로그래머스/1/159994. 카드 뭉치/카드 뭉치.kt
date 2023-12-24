class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var idx1 = 0
        var idx2 = 0
        for (word in goal) {
            if (idx1 <= cards1.lastIndex && cards1[idx1] == word) {
                idx1 += 1
                continue
            }
            if (idx2 <= cards2.lastIndex && cards2[idx2] == word) {
                idx2 += 1
                continue
            }
            return "No"
        }
        return "Yes"
    }
}