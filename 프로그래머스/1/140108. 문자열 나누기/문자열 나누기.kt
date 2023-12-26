class Solution {
    fun solution(s: String): Int {
        var answer = 1
        var idx = 0
        var xCnt = 0
        var notXCnt = 0
        var x = s[idx]
        while (idx < s.length) {
            if (s[idx] == x) {
                xCnt++
            } else {
                notXCnt++
            }
            if (++idx == s.length) break
            if (xCnt == notXCnt) {
                answer++
                xCnt = 0
                notXCnt = 0
                x = s[idx]
            }
        }
        return answer
    }
}